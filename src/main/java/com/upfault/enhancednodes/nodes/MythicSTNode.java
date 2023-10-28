package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;
import java.util.Random;

public class MythicSTNode extends NodeBase {

	public MythicSTNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§dMythic Node - Smelting Touch Add-on");
		setLore(getLore());
		setGlowing(true);
		setUnbreakable(true);
	}

	public List<String> getLore() {
		Random random = new Random();
		int firstPercentage = random.nextInt(101);
		int secondPercentage = 100 - firstPercentage;
		return List.of("§aThis is an enhanced node.", " ", "§7Grant's the ability to add", "§7Smelting Touch on pickaxe's.", " ", "§7Chance of Success: " + "§a" + firstPercentage + "%",  "§7Chance of Failure: " + "§c" + secondPercentage + "%", " ", "§d§lMYTHIC NODE");
	}
}
