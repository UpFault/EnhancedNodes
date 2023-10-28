package com.upfault.enhancednodes.guis;

import com.upfault.enhancednodes.nodes.*;
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

import java.util.ArrayList;
import java.util.List;

public class CheatSheet implements Listener {
	private static final Inventory cheatSheet = Bukkit.createInventory(null, 54, "Cheat Sheet");

	public CheatSheet() {
		initializeInventory();
	}

	public void openInventory(Player player) {
		player.openInventory(cheatSheet);
	}

	private void initializeInventory() {
		ItemStack border = createItem(Material.BLACK_STAINED_GLASS_PANE, "§l");
		ItemStack closeMenu = createItem(Material.BARRIER, "§cClose Menu");

		for (int i = 0; i < 54; i++) {
			cheatSheet.setItem(i, (i < 9 || i >= 45 || i % 9 == 0 || (i + 1) % 9 == 0) ? border : new ItemStack(Material.AIR));
		}

		cheatSheet.setItem(49, closeMenu);

		List<ItemStack> itemStackList = List.of(
				new CommonNode().createItem(),
				new UncommonNode().createItem(),
				new RareNode().createItem(),
				new EpicNode().createItem(),
				new LegendaryNode().createItem(),
				new MythicTKNode().createItem(),
				new MythicSTNode().createItem(),
				new MysteriousFragment().createItemWithoutNBT(),
				new NodeForge().createNode()
		);

		int[] allowedSlots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};

		int itemIndex = 0;
		for (int slot : allowedSlots) {
			if (itemIndex < itemStackList.size()) {
				ItemStack item = itemStackList.get(itemIndex);
				adjustLoreForItem(item);
				cheatSheet.setItem(slot, item);
				itemIndex++;
			}
		}
	}

	private void adjustLoreForItem(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		if (meta != null && meta.hasLore()) {
			List<String> lore = new ArrayList<>(meta.getLore());
			if (lore.size() >= 7) {
				lore.subList(4, 7).clear();
				meta.setLore(lore);
				item.setItemMeta(meta);
			}
		}
	}

	private ItemStack createItem(Material material, String displayName) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName(displayName);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
		}
		return item;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (event.getClickedInventory() != null && event.getClickedInventory().equals(cheatSheet)) {
			event.setCancelled(true);

			switch (event.getSlot()) {
				case 10 -> addItemToInventory(player, new CommonNode().createItem());
				case 11 -> addItemToInventory(player, new UncommonNode().createItem());
				case 12 -> addItemToInventory(player, new RareNode().createItem());
				case 13 -> addItemToInventory(player, new EpicNode().createItem());
				case 14 -> addItemToInventory(player, new LegendaryNode().createItem());
				case 15 -> addItemToInventory(player, new MythicTKNode().createItem());
				case 16 -> addItemToInventory(player, new MythicSTNode().createItem());
				case 19 -> addItemToInventory(player, new MysteriousFragment().createItemWithoutNBT());
				case 20 -> addItemToInventory(player, new NodeForge().createNode());
				case 49 -> player.closeInventory();
			}
		}
	}

	private void addItemToInventory(Player player, ItemStack item) {
		if (player.getInventory().firstEmpty() == -1) {
			player.sendMessage("§cYour inventory is full.");
		} else {
			player.getInventory().addItem(item);
		}
	}
}
