package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class RareNode extends NodeBase {

	public RareNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§9Rare Node - Efficiency Upgrade");
		setLore(List.of("§aThis is an enhanced node.", " ", "§7Grant's the ability to upgrade", "§7efficiency on pickaxe's.", " ", "§9§lRARE NODE"));
		setGlowing(true);
		setUnbreakable(true);
	}
}
