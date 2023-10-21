package com.upfault.enhancednodes.utils;

import com.upfault.enhancednodes.EnhancedNodes;
import fi.iki.elonen.NanoHTTPD;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocalWebServer extends NanoHTTPD {
	private final ExecutorService clientThreads = Executors.newCachedThreadPool();
	private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
	private boolean isShuttingDown = false;
	private final ScheduledExecutorService viewerChecker = Executors.newScheduledThreadPool(1);

	public LocalWebServer(int port) throws IOException {
		super(port);
		startViewerChecker();
	}

	@Override
	public Response serve(IHTTPSession session) {
		String htmlResponse = readHtmlFile();

		if (htmlResponse != null) {
			resetShutdownTimer();
			return newFixedLengthResponse(Response.Status.OK, "text/html", htmlResponse);
		} else {
			return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/html", "File not found");
		}
	}

	private String readHtmlFile() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream is = classLoader.getResourceAsStream("src/main/java/com/upfault/enhancednodes/webserver/index.html");

			if (is != null) {
				InputStreamReader reader = new InputStreamReader(is);
				BufferedReader bufferedReader = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line).append("\n");
				}
				return sb.toString();
			}
		} catch (IOException e) {
			EnhancedNodes.getInstance().logError(e.getMessage());
		}
		return "<html><body><h1>Error: Issue Locating index.html, Please contact the developer of this plugin to resolve this issue</h1></body></html>";
	}

	public void startServer() {
		try {
			start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
			EnhancedNodes.getInstance().logInfo("Local web server started.");
			startViewerChecker();
		} catch (IOException e) {
			EnhancedNodes.getInstance().logError("Error starting local web server: " + e.getMessage());
		}
	}


	private void startViewerChecker() {
		viewerChecker.scheduleAtFixedRate(() -> {
			if (!isShuttingDown && !isAlive()) {
				EnhancedNodes.getInstance().logInfo("No active viewers detected. Stopping local web server.");
				stopServer();
			}
		}, 1, 1, TimeUnit.MINUTES); // Check every minute
	}

	public void stopServer() {
		if (isShuttingDown) {
			return;
		}

		isShuttingDown = true;
		stop();
		clientThreads.shutdownNow();
		EnhancedNodes.getInstance().logInfo("Local web server is scheduled to stop in 30 seconds.");
		scheduleShutdown();
	}

	private void resetShutdownTimer() {
		if (isShuttingDown) {
			timer.shutdownNow();
			isShuttingDown = false;
			EnhancedNodes.getInstance().logInfo("Local web server will remain active.");
		}
	}

	private void scheduleShutdown() {
		timer.schedule(() -> {
			stop();
			EnhancedNodes.getInstance().logInfo("Local web server stopped.");
		}, 30, TimeUnit.SECONDS);
	}

	public boolean isRunning() {
		return (isAlive());
	}

	public String getWebURL() {
		return "http://localhost:" + getListeningPort();
	}
}
