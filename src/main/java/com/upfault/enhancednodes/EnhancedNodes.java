package com.upfault.enhancednodes;

import com.upfault.enhancednodes.commands.checkKeyCommand;
import com.upfault.enhancednodes.commands.enhancedNodesCommand;
import com.upfault.enhancednodes.crafts.CraftingRecipes;
import com.upfault.enhancednodes.guis.AdminPanel;
import com.upfault.enhancednodes.listeners.BlockBreakListener;
import com.upfault.enhancednodes.listeners.BlockPlaceListener;
import com.upfault.enhancednodes.listeners.CraftItemListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EnhancedNodes extends JavaPlugin {
    private Logger logger;
    private static EnhancedNodes instance;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();

        PluginManager pluginManager = getServer().getPluginManager();
        Plugin nbtApiPlugin = pluginManager.getPlugin("NBTAPI");

        if (nbtApiPlugin == null || !nbtApiPlugin.isEnabled()) {
            logError("NBTAPI is required for this plugin to work. Disabling...");
            logInfo("You can download the plugin here: https://www.spigotmc.org/resources/nbt-api.7939/");
            pluginManager.disablePlugin(this);
            return;
        }

        registerEvents();
        registerCommands();
        registerCrafts();
    }

    private void registerCrafts() {
        CraftingRecipes.registerNodeForgeRecipe(this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getServer().getPluginCommand("enhancednodes")).setExecutor(new enhancedNodesCommand());
        Objects.requireNonNull(getServer().getPluginCommand("enhancednodes")).setTabCompleter(new enhancedNodesCommand());
        Objects.requireNonNull(getServer().getPluginCommand("checkkey")).setExecutor(new checkKeyCommand());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new AdminPanel(), this);
        getServer().getPluginManager().registerEvents(new CraftItemListener(), this);
    }

    @Override
    public void onDisable() {
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
