package com.upfault.enhancednodes.commands;

import com.upfault.enhancednodes.EnhancedNodes;
import com.upfault.enhancednodes.guis.AdminPanel;
import com.upfault.enhancednodes.utils.LocalWebServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class enhancedNodesCommand implements CommandExecutor, TabCompleter {

	private final LocalWebServer localWebServer;

	public enhancedNodesCommand(LocalWebServer localWebServer) {
		this.localWebServer = localWebServer;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(!(sender instanceof Player player)) {
			EnhancedNodes.getInstance().logWarning("You can not use this command!");
			return true;
		}

		if(!player.hasPermission("enhancednodes.admin") || !player.isOp()) {
			player.sendMessage("§cYou do not have the proper permission's to do that!");
		}

		switch (args[0].toLowerCase()) {
			case "panel" -> {
				new AdminPanel(localWebServer).openInventory(player);
				return true;
			}
			case "help" -> {
				player.sendMessage("§e§l-------------------------------------------");
				player.sendMessage("§aCommand Aliases §f- §e/en");
				player.sendMessage("§a/enhancednodes help §f- §eDisplay's command information.");
				player.sendMessage("§a/enhancednodes info §f- §eDisplay's information about the plugin.");
				player.sendMessage("§a/enhancednodes panel §f- §eDisplay's the admin panel.");
				player.sendMessage("§e§l-------------------------------------------");
				return true;
			}
			case "info" -> {
				player.sendMessage("§e§l-------------------------------------------");
				player.sendMessage("§aPlugin Name: " + EnhancedNodes.getInstance().getDescription().getName());
				player.sendMessage("§aVersion: " + EnhancedNodes.getInstance().getDescription().getVersion());
				player.sendMessage("§aAuthor: " + EnhancedNodes.getInstance().getDescription().getAuthors().get(0));
				player.sendMessage("§9GitHub Repository: " + EnhancedNodes.getInstance().getDescription().getWebsite());
				player.sendMessage("§e§l-------------------------------------------");
				return true;
			}
			default -> {
				player.sendMessage("§cUnknown argument. Try §f\"/enhancednodes help\" §cfor more information.");
				return true;
			}
		}
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		List<String> completions = Arrays.asList("help", "info", "panel");

		if (args.length == 1) {
			return completions;
		}

		return null;
	}
}
