package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.EnhancedNodes;
import com.upfault.enhancednodes.nodes.NodeForge;
import com.upfault.enhancednodes.utils.TriMap;
import de.tr7zw.nbtapi.NBTItem;
import javafx.util.Pair;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class BlockPlaceListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getItemInHand().getType() == Material.AIR) return;
		NBTItem nbtItem = new NBTItem(event.getItemInHand());
		String value = nbtItem.getString("en_identifier");
		Long value2 = nbtItem.getLong("en_time_created");

		if(event.getBlock().getType() == Material.SMITHING_TABLE && event.getItemInHand().equals(new NodeForge().createItemWithoutNBT()) && (nbtItem.getString("en_identifier") == null && nbtItem.getString("en_time_created") == null)) {
			event.setCancelled(true);
			return;
		}

		if (event.getBlock().getType() == Material.SMITHING_TABLE && nbtItem.hasCustomNbtData()) {
			if (nbtItem.getString("en_identifier") == null || nbtItem.getString("en_time_created") == null) return;

			Block block = event.getBlock();
			PersistentDataContainer dataContainer = block.getChunk().getPersistentDataContainer();

			NamespacedKey keyIdentifier = new NamespacedKey(EnhancedNodes.getInstance(), "en_identifier");
			dataContainer.set(keyIdentifier, PersistentDataType.STRING, value);

			NamespacedKey keyTimeCreated = new NamespacedKey(EnhancedNodes.getInstance(), "en_time_created");
			dataContainer.set(keyTimeCreated, PersistentDataType.LONG, value2);

			block.setBlockData(block.getBlockData(), dataContainer.has(keyIdentifier));
			block.setBlockData(block.getBlockData(), dataContainer.has(keyTimeCreated));
			 block.getState().update();
		}
	}
}
