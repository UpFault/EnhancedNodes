package com.upfault.enhancednodes.guis;

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

public class AdminPanel implements Listener {
	public static Inventory adminInventory;

	public AdminPanel() {
		adminInventory = Bukkit.createInventory(null, 27, "Admin Panel");
	}

	public void openInventory(Player player) {
		ItemMeta meta;

		ItemStack imb = createMenuItem(Material.FIREWORK_STAR, "§aView All Items", "§7Do a little cheating and spawn", "§7in some items. §e:)");
		ItemStack smb = createMenuItem(Material.FIREWORK_STAR, "§aView Statistics", "§7View some statistics about", "§7Item drops, crafts, and more!", "§f ", "§cThis feature is still experimental!");
		ItemStack pb = createMenuItem(Material.BEDROCK, "§c§lCOMING SOON!");
		ItemStack border = createBorderItem();
		ItemStack cmb = createCloseMenuItem();

		adminInventory.setItem(10, imb);
		adminInventory.setItem(11, smb);
		adminInventory.setItem(22, cmb);

		for (int i = 12; i < 17; i++) {
			adminInventory.setItem(i, pb);
		}

		for (int i = 0; i < 27; i++) {
			if (adminInventory.getItem(i) == null) {
				adminInventory.setItem(i, border);
			}
		}

		player.openInventory(adminInventory);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory clickedInventory = event.getClickedInventory();
		if (clickedInventory != null && clickedInventory.getType() == InventoryType.CHEST && event.getClickedInventory() == adminInventory) {
			event.setCancelled(true);

			int slot = event.getSlot();
			if (slot == 10) {
				new CheatSheet().openInventory(player);
			} else if (slot == 11) {
//				player.sendMessage("§aCreating spreadsheet of plugin statistics.");
//				player.sendMessage("§ago into /plugins/EnhancedNodes/statistics to view your server statistics!");
				player.closeInventory();
			} else if (slot == 22) {
				player.closeInventory();
			}
		}
	}

	private ItemStack createMenuItem(Material material, String displayName, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(List.of(lore));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}

	private ItemStack createBorderItem() {
		ItemStack border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta borderMeta = border.getItemMeta();
		borderMeta.setDisplayName("§l");
		borderMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		border.setItemMeta(borderMeta);
		return border;
	}

	private ItemStack createCloseMenuItem() {
		ItemStack cmb = new ItemStack(Material.BARRIER);
		ItemMeta cmbMeta = cmb.getItemMeta();
		cmbMeta.setDisplayName("§cClose Menu");
		cmbMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cmb.setItemMeta(cmbMeta);
		return cmb;
	}
}
