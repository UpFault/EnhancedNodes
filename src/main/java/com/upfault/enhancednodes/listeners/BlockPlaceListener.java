package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.utils.DataMap;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		NBTItem nbtItem = new NBTItem(event.getItemInHand());
		String value = nbtItem.getString("en_identifier");

		if (event.getBlock().getType() == Material.SMITHING_TABLE && nbtItem.hasCustomNbtData()) {
			if(nbtItem.getString("en_identifier") == null) return;
			Location location = event.getBlock().getLocation();
			DataMap.addData(location, value);
		}
	}
}
