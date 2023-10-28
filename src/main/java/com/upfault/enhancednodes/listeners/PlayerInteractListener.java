package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.EnhancedNodes;
import com.upfault.enhancednodes.guis.NodeForgePanel;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerInteractListener implements Listener {
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent event) {
		Block clickedBlock = event.getClickedBlock();
		Player player = event.getPlayer();

		if (clickedBlock != null && clickedBlock.getType() == Material.SMITHING_TABLE && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			PersistentDataContainer dataContainer = clickedBlock.getChunk().getPersistentDataContainer();
			NamespacedKey keyIdentifier = new NamespacedKey(EnhancedNodes.getInstance(), "en_identifier");
			NamespacedKey keyTimeCreated = new NamespacedKey(EnhancedNodes.getInstance(), "en_time_created");
			NamespacedKey isForgeKey = new NamespacedKey(EnhancedNodes.getInstance(), "en_isForge");

			if (dataContainer.has(keyIdentifier, PersistentDataType.STRING)
					&& dataContainer.has(keyTimeCreated, PersistentDataType.LONG)
					&& dataContainer.has(isForgeKey, PersistentDataType.STRING)) {
				event.setCancelled(true);
				new NodeForgePanel().openInventory(player);
			}
		}
	}

}
