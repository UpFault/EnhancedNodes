package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class MythicSTNode extends NodeBase {

	public MythicSTNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§dMythic Node - Smelting Touch Add-on");
		setLore(List.of("§aThis is an enhanced node.", "Grant's the ability to add", "§7Smelting Touch on pickaxe's.", " ", "§d§lMYTHIC NODE"));
		setGlowing(true);
		setUnbreakable(true);
	}
}
