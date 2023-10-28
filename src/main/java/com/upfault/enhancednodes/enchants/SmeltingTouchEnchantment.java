package com.upfault.enhancednodes.enchants;

import com.upfault.enhancednodes.EnhancedNodes;
import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmeltingTouchEnchantment extends Enchantment {

	public SmeltingTouchEnchantment() {
		super(NamespacedKey.minecraft("en_smeltingtouch"));
	}

	@Override
	public @NotNull String getName() {
		return "Smelting Touch";
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public @NotNull EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.TOOL;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean conflictsWith(@NotNull Enchantment enchantment) {
		return enchantment.equals(Enchantment.SILK_TOUCH);
	}

	@Override
	public boolean canEnchantItem(ItemStack item) {
		return item.getType().toString().endsWith("PICKAXE");
	}

	@Override
	public @NotNull Component displayName(int i) {
		return null;
	}

	@Override
	public boolean isTradeable() {
		return false;
	}

	@Override
	public boolean isDiscoverable() {
		return false;
	}

	@Override
	public @NotNull EnchantmentRarity getRarity() {
		return EnchantmentRarity.VERY_RARE;
	}

	@Override
	public float getDamageIncrease(int i, @NotNull EntityCategory entityCategory) {
		return 0;
	}

	@Override
	public @NotNull Set<EquipmentSlot> getActiveSlots() {
		Set<EquipmentSlot> slots = new HashSet<>();
		slots.add(EquipmentSlot.HAND);

		return slots;
	}

	@Override
	public @NotNull String translationKey() {
		return "enhancednodes.smeltingtouch";
	}

	public static void register() {
		Enchantment enchantment = new SmeltingTouchEnchantment();
		try {
			Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
			acceptingNew.setAccessible(true);
			acceptingNew.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked"})
	public static void unregister() {
		Enchantment enchantment = new SmeltingTouchEnchantment();
		try {
			Field byKeyField = Enchantment.class.getDeclaredField("byKey");
			Field byNameField = Enchantment.class.getDeclaredField("byName");
			byKeyField.setAccessible(true);
			byNameField.setAccessible(true);
			Map<NamespacedKey, Enchantment> byKey = (Map<NamespacedKey, Enchantment>) byKeyField.get(null);
			Map<String, Enchantment> byName = (Map<String, Enchantment>) byNameField.get(null);
			byKey.remove(enchantment.getKey());
			byName.remove(enchantment.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

