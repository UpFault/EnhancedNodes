package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.EnhancedNodes;
import com.upfault.enhancednodes.guis.NodeForgePanel;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

	private final NamespacedKey keyIdentifier = new NamespacedKey(EnhancedNodes.getInstance(), "en_identifier");
	private final NamespacedKey keyTimeCreated = new NamespacedKey(EnhancedNodes.getInstance(), "en_time_created");
	private final NamespacedKey isForgeKey = new NamespacedKey(EnhancedNodes.getInstance(), "en_isForge");

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block clickedBlock = player.getTargetBlock(null, 5);

		if (player.getOpenInventory().getTopInventory() == NodeForgePanel.getInventory() && !isNearSmithingTable(clickedBlock)) {
			player.sendMessage("§cYou are to far from the Node Forge!");
			player.closeInventory();
		}
	}

	private boolean isNearSmithingTable(Block block) {
		if (block.getType().toString().contains("SMITHING_TABLE")) {
			return hasRequiredTags(block);
		}
		return false;
	}

	private boolean hasRequiredTags(Block block) {
		if (block != null) {
			block.getChunk();
			return block.getChunk().getPersistentDataContainer().has(keyIdentifier) && block.getChunk().getPersistentDataContainer().has(keyTimeCreated) && block.getChunk().getPersistentDataContainer().has(isForgeKey);
		}
		return false;
	}
}
