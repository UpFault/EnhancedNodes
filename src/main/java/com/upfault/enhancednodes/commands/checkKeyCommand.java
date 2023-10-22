package com.upfault.enhancednodes.commands;

import com.upfault.enhancednodes.EnhancedNodes;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class checkKeyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player player)) {
			EnhancedNodes.getInstance().logWarning("You cannot use this command!");
			return true;
		}

		if (!player.hasPermission("enhancednodes.admin") && !player.isOp()) {
			player.sendMessage("§cYou do not have the proper permissions to do that!");
			return true;
		}

		ItemStack heldItem = player.getInventory().getItemInMainHand();

		if (heldItem == null || heldItem.getType() == Material.AIR) {
			player.sendMessage("§cYou need to be holding an item to see if it has a key!");
			return true;
		}

		NBTItem nbtItem = new NBTItem(heldItem);
		String value = nbtItem.getString("en_identifier");
		Long value2 = nbtItem.getLong("en_time_created");

		if(heldItem.getItemMeta().getDisplayName().equals("§aMysterious Fragment")) {
			player.sendMessage("§aThis is an EnhancedNodes Item but has no key!");
			return true;
		}

		if (value == null || !nbtItem.hasCustomNbtData()) {
			player.sendMessage("§cThis item is either not an EnhancedNodes item or does not have a key!");
		} else {

			Date date = new Date(value2);

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy 'at' hh:mm:ssa");
			String formattedDate = dateFormat.format(date);
			player.sendMessage("§aThis is an EnhancedNodes item and has the UUID §e" + value + "§a, this item was created on §e" + formattedDate);
		}


		return true;
	}
}
