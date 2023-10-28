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
	public void onForgePlace(BlockPlaceEvent event) {
		if(event.getItemInHand().getType() == Material.AIR) return;
		NBTItem nbtItem = new NBTItem(event.getItemInHand());
		String identifier = nbtItem.getString("en_identifier");
		Long timeCreated = nbtItem.getLong("en_time_created");
		Boolean isForge = nbtItem.getBoolean("en_isForge");

		if (event.getBlock().getType() == Material.SMITHING_TABLE && nbtItem.hasCustomNbtData()) {
			if (nbtItem.getString("en_identifier") == null || nbtItem.getString("en_time_created") == null || nbtItem.getBoolean("en_isForge") == null) return;

			Block block = event.getBlock();
			PersistentDataContainer dataContainer = block.getChunk().getPersistentDataContainer();

			NamespacedKey keyIdentifier = new NamespacedKey(EnhancedNodes.getInstance(), "en_identifier");
			NamespacedKey keyTimeCreated = new NamespacedKey(EnhancedNodes.getInstance(), "en_time_created");
			NamespacedKey isForgeKey = new NamespacedKey(EnhancedNodes.getInstance(), "en_isForge");

			dataContainer.set(keyIdentifier, PersistentDataType.STRING, identifier);
			dataContainer.set(keyIdentifier, PersistentDataType.LONG, timeCreated);
			dataContainer.set(keyIdentifier, PersistentDataType.STRING, isForge.toString());

			block.setBlockData(block.getBlockData(), dataContainer.has(keyIdentifier));
			block.setBlockData(block.getBlockData(), dataContainer.has(keyTimeCreated));
			block.setBlockData(block.getBlockData(), dataContainer.has(isForgeKey));
			block.getState().update();
		}
	}
}
