package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.nodes.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class BlacklistListener implements Listener {

	@EventHandler
	public void onPlayerPickupItem(PlayerAttemptPickupItemEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem().getItemStack();
		if (isBlacklistedItem(item.asOne())) {
			event.setCancelled(true);
			item.setAmount(0);
			player.sendMessage("§cRemoved Blacklisted item from your inventory!");
		}
	}

	@EventHandler
	public void onHeld(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		int slot = event.getNewSlot();
		int prevSlot = event.getPreviousSlot();
		ItemStack item = player.getInventory().getItem(slot);
		ItemStack prevItem = player.getInventory().getItem(prevSlot);

		if (item != null && isBlacklistedItem(item.asOne())) {
			item.setAmount(0);
			player.sendMessage("§cRemoved Blacklisted item from your inventory!");
		}
		if (prevItem != null && isBlacklistedItem(prevItem.asOne())) {
			prevItem.setAmount(0);
			player.sendMessage("§cRemoved Blacklisted item from your inventory!");
		}
	}




	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		if (isBlacklistedItem(item.asOne())) {
			event.setCancelled(true);
			item.setAmount(0);
			player.sendMessage("§cRemoved Blacklisted item from your inventory!");
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItemDrop().getItemStack();
		if (isBlacklistedItem(item.asOne())) {
			event.setCancelled(true);
			item.setAmount(0);
			player.sendMessage("§cRemoved Blacklisted item from your inventory!");
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getItemInHand().getType() != null) {
			if(isBlacklistedItem(event.getItemInHand().asOne())) {
				event.setCancelled(true);
				event.getItemInHand().setAmount(0);
			}
		}
	}

	private boolean isBlacklistedItem(ItemStack item) {
		return item.equals(new CommonNode().createItemWithoutNBT()) ||
				item.equals(new UncommonNode().createItemWithoutNBT()) ||
				item.equals(new RareNode().createItemWithoutNBT()) ||
				item.equals(new EpicNode().createItemWithoutNBT()) ||
				item.equals(new LegendaryNode().createItemWithoutNBT()) ||
				item.equals(new MythicSTNode().createItemWithoutNBT()) ||
				item.equals(new MythicTKNode().createItemWithoutNBT()) ||
				item.equals(new NodeForge().createItem()) ||
				item.equals(new NodeForge().createItemWithoutNBT());
	}
}
