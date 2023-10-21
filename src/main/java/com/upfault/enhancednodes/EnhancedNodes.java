package com.upfault.enhancednodes;

import com.upfault.enhancednodes.commands.enhancedNodesCommand;
import com.upfault.enhancednodes.guis.AdminPanel;
import com.upfault.enhancednodes.listeners.BlockBreakListener;
import com.upfault.enhancednodes.utils.LocalWebServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EnhancedNodes extends JavaPlugin {
    private Logger logger;
    private static EnhancedNodes instance;
    private LocalWebServer localWebServer;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        try {
            localWebServer = new LocalWebServer(8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        registerEvents();
        registerCommands();
    }


    private void registerCommands() {
        Objects.requireNonNull(getServer().getPluginCommand("enhancednodes")).setExecutor(new enhancedNodesCommand(localWebServer));
        Objects.requireNonNull(getServer().getPluginCommand("enhancednodes")).setTabCompleter(new enhancedNodesCommand(localWebServer));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new AdminPanel(localWebServer), this);
    }

    @Override
    public void onDisable() {
       localWebServer.stop();
    }

    public static EnhancedNodes getInstance() {
        return instance;
    }

    public void logInfo(String message) {
        log(Level.INFO, message);
    }

    public void logWarning(String message) {
        log(Level.WARNING, message);
    }

    public void logError(String message) {
        log(Level.SEVERE, message);
    }

    private void log(Level level, String message) {
		logger.log(level, message);
    }
}
