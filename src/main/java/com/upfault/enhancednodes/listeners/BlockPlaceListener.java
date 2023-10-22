package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.utils.TriMap;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.UUID;

public class BlockPlaceListener implements Listener {

	public static TriMap<Location, String, Long> dataMap = new TriMap<>();

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		NBTItem nbtItem = new NBTItem(event.getItemInHand());
		String value = nbtItem.getString("en_identifier");
		Long value2 = nbtItem.getLong("en_time_created");

		if (event.getBlock().getType() == Material.SMITHING_TABLE && nbtItem.hasCustomNbtData()) {
			if(nbtItem.getString("en_identifier") == null && nbtItem.getString("en_time_created") == null) return;
			Location location = event.getBlock().getLocation();
			dataMap.put(location, value, value2);
		}
	}
}
