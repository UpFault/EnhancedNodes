package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class LegendaryNode extends NodeBase {

	public LegendaryNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§6Legendary Node - Fortune Upgrade");
		setLore(List.of("§aThis is an enhanced node.", "Grant's the ability to upgrade", "§7Fortune on pickaxe's.", " ", "§6§lLEGENDARY NODE"));
		setGlowing(true);
		setUnbreakable(true);
	}
}
