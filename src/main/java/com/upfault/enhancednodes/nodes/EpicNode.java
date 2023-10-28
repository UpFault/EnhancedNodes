package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EpicNode extends NodeBase {
	public EpicNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§5Epic Node - Efficiency Upgrade");
		setLore(getLore());
		setGlowing(true);
		setUnbreakable(true);
	}

	public List<String> getLore() {
		Random random = new Random();
		int firstPercentage = random.nextInt(11) + 40;
		int secondPercentage = 100 - firstPercentage;

		return List.of(
				"§aThis is an enhanced node.",
				" ",
				"§7Grant's the ability to upgrade",
				"§7efficiency on pickaxe's.",
				" ",
				"§7Chance of Success: " + "§a" + firstPercentage + "%",
				"§7Chance of Failure: " + "§c" + secondPercentage + "%",
				" ",
				"§5§lEPIC NODE"
		);
	}
}
