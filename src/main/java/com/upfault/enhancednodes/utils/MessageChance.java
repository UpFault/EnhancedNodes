package com.upfault.enhancednodes.utils;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public record MessageChance(double chance, String message, Sound sound, ItemStack item) {


}
