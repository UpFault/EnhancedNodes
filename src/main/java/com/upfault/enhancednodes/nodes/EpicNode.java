package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class EpicNode extends NodeBase {

	public EpicNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§5Epic Node - Efficiency Upgrade");
		setLore(List.of("§aThis is an enhanced node.", " ", "§7Grant's the ability to upgrade", "§7efficiency on pickaxe's.", " ", "§5§lEPIC NODE"));
		setGlowing(true);
		setUnbreakable(true);
	}
}
