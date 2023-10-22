package com.upfault.enhancednodes.nodes;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public abstract class NodeBase {
	protected Material material;
	protected String customName;
	protected List<String> lore;
	protected boolean glowing;
	protected boolean unbreakable;

	public NodeBase(Material material) {
		this.material = material;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public void setGlowing(boolean glowing) {
		this.glowing = glowing;
	}

	public void setUnbreakable(boolean unbreakable) {
		this.unbreakable = unbreakable;
	}

	public ItemStack createItem() {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();

		if (customName != null) {
			meta.setDisplayName(customName);
		}

		if (lore != null) {
			meta.setLore(lore);
		}

		if (glowing) {
			meta.addEnchant(Enchantment.LUCK, 1, true);
		}

		if (unbreakable) {
			meta.setUnbreakable(true);
		}

		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		item.setItemMeta(meta);

		NBT.modify(item, nbt -> {
			nbt.setString("en_identifier", String.valueOf(UUID.randomUUID()));
			nbt.setLong("en_time_created", System.currentTimeMillis());
		});

		return item;
	}

	public ItemStack createItemWithoutNBT() {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();

		if (customName != null) {
			meta.setDisplayName(customName);
		}

		if (lore != null) {
			meta.setLore(lore);
		}

		if (glowing) {
			meta.addEnchant(Enchantment.LUCK, 1, true);
		}

		if (unbreakable) {
			meta.setUnbreakable(true);
		}

		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		item.setItemMeta(meta);
		return item;
	}
}

