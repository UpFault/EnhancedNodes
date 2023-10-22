package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.EnhancedNodes;
import com.upfault.enhancednodes.guis.NodeForgePanel;
import com.upfault.enhancednodes.nodes.*;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerInteractListener implements Listener {
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent event) {
		Block clickedBlock = event.getClickedBlock();

		if (clickedBlock != null && clickedBlock.getType() == Material.SMITHING_TABLE) {
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.getPlayer().closeInventory();
				PersistentDataContainer dataContainer = clickedBlock.getChunk().getPersistentDataContainer();
				NamespacedKey keyIdentifier = new NamespacedKey(EnhancedNodes.getInstance(), "en_identifier");

				if (dataContainer.has(keyIdentifier, PersistentDataType.STRING)) {
					Player player = event.getPlayer();
					new NodeForgePanel().openInventory(player);
				}
			}
		}
	}
}
