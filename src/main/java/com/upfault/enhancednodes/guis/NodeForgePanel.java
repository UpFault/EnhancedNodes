package com.upfault.enhancednodes.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class NodeForgePanel implements Listener {
	private static final Inventory nodeForgeInventory = Bukkit.createInventory(null, 54, "§eNode Forge");

	public NodeForgePanel() {
		fillInventory();
	}

	public static void openInventory(Player player) {
		player.openInventory(nodeForgeInventory);
	}

	private void fillInventory() {
		for (int i = 0; i < 54; i++) {
			nodeForgeInventory.setItem(i, createItem(Material.GRAY_STAINED_GLASS_PANE, "§7 "));
		}

		nodeForgeInventory.setItem(13, createItem(Material.END_PORTAL_FRAME, "§aApply a Node", "§7Add the rune to your provided", "§7item to attempt to fuse them."));
		nodeForgeInventory.setItem(19, new ItemStack(Material.AIR));
		nodeForgeInventory.setItem(25, new ItemStack(Material.AIR));
		nodeForgeInventory.setItem(31, createItem(Material.BARRIER, "§eNode Forge", "§7Place a target item in the left", "§7slot and a sacrifice node in the", "§7right slot to add the node's", "§7effects to the item."));
		nodeForgeInventory.setItem(49, createItem(Material.BARRIER, "§cClose Menu"));

		for (int i = 10; i < 13; i++) {
			nodeForgeInventory.setItem(i, createItem(Material.WHITE_STAINED_GLASS_PANE, "§6Item to Upgrade", "§7The pickaxe you want to upgrade you should", "§7place in the slot on this side."));
		}

		for (int i = 14; i < 17; i++) {
			nodeForgeInventory.setItem(i, createItem(Material.WHITE_STAINED_GLASS_PANE, "§6Node to Sacrifice", "§7The node you're sacrificing in", "§7order to upgrade the item on the", "§7left should be placed in the", "§7slot on this side."));
		}

		for (int i = 45; i < 54; i++) {
			nodeForgeInventory.setItem(i, createItem(Material.WHITE_STAINED_GLASS_PANE, "§7 "));
		}
	}

	private ItemStack createItem(Material material, String displayName, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(Arrays.asList(lore));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equals("§eNode Forge")) {
			event.setCancelled(true);
		}
	}
}
