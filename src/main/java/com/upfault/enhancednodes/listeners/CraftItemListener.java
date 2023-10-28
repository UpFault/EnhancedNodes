package com.upfault.enhancednodes.listeners;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.UUID;

public class CraftItemListener implements Listener {

	@EventHandler
	public void PrepareItemCraftEvent(PrepareItemCraftEvent event) {
		if (event.getRecipe() instanceof ShapedRecipe recipe) {
			if (recipe.getKey().getKey().equals("enhancednodes_node_forge")) {
				ItemStack result = event.getInventory().getResult();
				if (result != null) {
					NBT.modify(result, nbt -> {
						nbt.setString("en_identifier", String.valueOf(UUID.randomUUID()));
						nbt.setLong("en_time_created", System.currentTimeMillis());
						nbt.setBoolean("en_isForge", true);
					});
				}
			}
		}
	}
}
