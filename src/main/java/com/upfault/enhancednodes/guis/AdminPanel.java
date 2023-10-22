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

@SuppressWarnings("deprecation")
public class AdminPanel implements Listener {
	public static Inventory adminInventory;

	public AdminPanel() {
		adminInventory = Bukkit.createInventory(null, 27, "Admin Panel");
	}

	public void openInventory(Player player) {

//		Item Menu Button
		ItemStack imb = new ItemStack(Material.FIREWORK_STAR);
		ItemMeta imbMeta = imb.getItemMeta();
		imbMeta.setDisplayName("§aView All Items");
		imbMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		List<String> imblore = List.of("§7Do a little cheating and spawn", "§7in some items. §e:)");
		imbMeta.setLore(imblore);
		imb.setItemMeta(imbMeta);
//		Statistic Menu Button
		ItemStack smb = new ItemStack(Material.FIREWORK_STAR);
		ItemMeta smbMeta = imb.getItemMeta();
		smbMeta.setDisplayName("§aView Statistics");
		smbMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		List<String> smblore = List.of("§7View some statistics about", "§7Item drops, crafts, and more!", "§f ", "§cThis feature is still experimental!");
		smbMeta.setLore(smblore);
		smb.setItemMeta(smbMeta);
//		Placeholder Button (does nothing)
		ItemStack pb = new ItemStack(Material.BEDROCK);
		ItemMeta pbMeta = pb.getItemMeta();
		pbMeta.setDisplayName("§c§lCOMING SOON!");
		pbMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pb.setItemMeta(pbMeta);
//		BORDER
		ItemStack border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta borderMeta = border.getItemMeta();
		borderMeta.setDisplayName("§l");
		borderMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		border.setItemMeta(borderMeta);
//		CLOSE MENU BUTTON
		ItemStack cmb = new ItemStack(Material.BARRIER);
		ItemMeta cmbMeta = cmb.getItemMeta();
		cmbMeta.setDisplayName("§cClose Menu");
		cmbMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cmb.setItemMeta(cmbMeta);


		for(int i = 0; i < 27; i++) {
			adminInventory.setItem(i, border);
		}

		adminInventory.setItem(10, imb);
		adminInventory.setItem(11, smb);
		adminInventory.setItem(22, cmb);

		for(int i = 12; i < 17; i++) {
			adminInventory.setItem(i, pb);
		}

		player.openInventory(adminInventory);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory clickedInventory = event.getClickedInventory();
		if (clickedInventory != null && clickedInventory.getType() == InventoryType.CHEST && event.getView().getTitle().equals("Admin Panel")) {
			event.setResult(Event.Result.DENY);
			event.setCancelled(true);
			if (event.getSlot() == 10) {
				new CheatSheet().openInventory(player);
			}

			if (event.getSlot() == 11) {

				player.sendMessage("§aCreating spreadsheet of plugin statistics.");
				player.sendMessage("§ago into /plugins/EnhancedNodes/statistics. to view your server statistics!");
				player.closeInventory();
//				if (LocalWebServer.isRunning()) {
//					new LocalWebServer().start();
//					player.sendMessage("§aOpening the plugin stats page in your web browser...");
//					player.sendMessage("§eTo view Plugin Statistics, enter this URL in your browser: §f§nhttp://localhost:8080");
//					player.closeInventory();
//				} else {
//					player.sendMessage("§cThe web server is not available or is currently running. Please try again later.");
//				}
			}


			if (event.getSlot() == 22) {
				event.getWhoClicked().closeInventory();
			}
		}
	}
}

