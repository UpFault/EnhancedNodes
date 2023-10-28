package com.upfault.enhancednodes;

import com.upfault.enhancednodes.commands.enhancedNodesCommand;
import com.upfault.enhancednodes.crafts.CraftingRecipes;
import com.upfault.enhancednodes.enchants.SmeltingTouchEnchantment;
import com.upfault.enhancednodes.enchants.TelekinesisEnchantment;
import com.upfault.enhancednodes.guis.AdminPanel;
import com.upfault.enhancednodes.guis.CheatSheet;
import com.upfault.enhancednodes.listeners.*;
import org.bukkit.Bukkit;
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

        registerCrafts();
        registerCommands();
        registerEvents();
        registerEnchants();
    }

    private void registerCrafts() {
        CraftingRecipes.registerNodeForgeRecipe(this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getServer().getPluginCommand("enhancednodes")).setExecutor(new enhancedNodesCommand());
        Objects.requireNonNull(getServer().getPluginCommand("enhancednodes")).setTabCompleter(new enhancedNodesCommand());
    }


    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new AdminPanel(), this);
        Bukkit.getPluginManager().registerEvents(new CheatSheet(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new BlacklistListener(), this);
        getServer().getPluginManager().registerEvents(new CraftItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
    }

    public void registerEnchants() {
        SmeltingTouchEnchantment.register();
        TelekinesisEnchantment.register();
    }

    @Override
    public void onDisable() {
        unRegisterEnchants();
    }

    private void unRegisterEnchants() {
        SmeltingTouchEnchantment.unregister();
        TelekinesisEnchantment.unregister();
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
