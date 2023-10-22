package com.upfault.enhancednodes.guis;

import com.upfault.enhancednodes.nodes.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@SuppressWarnings("deprecation")
public class CheatSheet implements Listener {
	public static Inventory cheatSheet;

	public CheatSheet() {
		cheatSheet = Bukkit.createInventory(null, 54, "Cheat Sheet");
	}

	public void openInventory(Player player) {

		ItemStack border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta borderMeta = border.getItemMeta();
		borderMeta.setDisplayName("§l");
		borderMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		border.setItemMeta(borderMeta);
		ItemStack cmb = new ItemStack(Material.BARRIER);
		ItemMeta cmbMeta = cmb.getItemMeta();
		cmbMeta.setDisplayName("§cClose Menu");
		cmbMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cmb.setItemMeta(cmbMeta);


		for (int i = 0; i < 54; i++) {
			if (i < 9 || i >= 45 || (i % 9 == 0) || ((i + 1) % 9 == 0)) {
				cheatSheet.setItem(i, border);
			} else {
				cheatSheet.setItem(i, new ItemStack(Material.AIR));
			}
		}

		cheatSheet.setItem(49, cmb);


		List<ItemStack> itemStackList = List.of(new CommonNode().createItem(), new UncommonNode().createItem(), new RareNode().createItem(), new EpicNode().createItem(), new LegendaryNode().createItem(), new MythicTKNode().createItem(), new MythicSTNode().createItem(), new MysteriousFragment().createItemWithoutNBT(), new NodeForge().createItem());

		int[] allowedSlots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};

		int itemIndex = 0;
		for (int slot : allowedSlots) {
			if (itemIndex < itemStackList.size()) {
				cheatSheet.setItem(slot, itemStackList.get(itemIndex));
				itemIndex++;
			}
		}

		player.openInventory(cheatSheet);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory clickedInventory = event.getClickedInventory();
		if (clickedInventory != null && clickedInventory.getType() == InventoryType.CHEST && event.getView().getTitle().equals("Cheat Sheet")) {
			event.setResult(Event.Result.DENY);
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
				case 20 -> addItemToInventory(player, new NodeForge().createItem());
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

