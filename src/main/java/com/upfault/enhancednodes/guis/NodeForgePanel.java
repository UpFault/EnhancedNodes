package com.upfault.enhancednodes.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NodeForgePanel {
	private static Inventory nodeForgeInventory = null;
	public static final Map<UUID, Inventory> playerInventories = new HashMap<>();
	public static final Map<UUID, Boolean> combiningNode = new HashMap<>();
	public NodeForgePanel() {
		nodeForgeInventory = Bukkit.createInventory(null, 54, "§eNode Forge");
		fillInventory();
	}

	public void openInventory(Player player) {
		playerInventories.put(player.getUniqueId(), nodeForgeInventory);
		player.openInventory(nodeForgeInventory);
	}

	public void fillInventory() {
		fillWithGlassPanes(0, 54, Material.GRAY_STAINED_GLASS_PANE, "§7 ");
		fillSpecialSlots();
	}

	private void fillWithGlassPanes(int start, int end, Material material, String displayName, String... lore) {
		for (int i = start; i < end; i++) {
			nodeForgeInventory.setItem(i, createItem(material, displayName, lore));
		}
	}

	public static Inventory getInventory() {
		return nodeForgeInventory;
	}

	private void fillSpecialSlots() {
		nodeForgeInventory.setItem(13, createItem(Material.END_PORTAL_FRAME, "§aApply a Node", "§7Add the rune to your provided", "§7item to attempt to fuse them."));
		fillWithAir();
		nodeForgeInventory.setItem(31, createItem(Material.BARRIER, "§eNode Forge", "§7Place a target item in the left", "§7slot and a sacrifice node in the", "§7right slot to add the node's", "§7effects to the item."));
		nodeForgeInventory.setItem(49, createItem(Material.BARRIER, "§cClose Menu"));
		nodeForgeInventory.setItem(22, createItem(Material.WHITE_STAINED_GLASS_PANE, "§7 "));
		fillWithGlassPanes(10, 13, Material.WHITE_STAINED_GLASS_PANE, "§6Item to Upgrade", "§7The pickaxe you want to upgrade you should", "§7place in the slot on this side.");
		fillWithGlassPanes(14, 17, Material.WHITE_STAINED_GLASS_PANE, "§6Node to Sacrifice", "§7The node you're sacrificing in", "§7order to upgrade the item on the", "§7left should be placed in the", "§7slot on this side.");
		fillWithGlassPanes(45, 54, Material.WHITE_STAINED_GLASS_PANE, "§7 ");
	}

	private void fillWithAir() {
		nodeForgeInventory.setItem(19, new ItemStack(Material.AIR));
		nodeForgeInventory.setItem(25, new ItemStack(Material.AIR));
	}

	private ItemStack createItem(Material material, String displayName, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName(displayName);
			meta.setLore(Arrays.asList(lore));
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
		}
		return item;
	}
}
