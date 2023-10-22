package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class MythicTKNode extends NodeBase {

	public MythicTKNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§dMythic Node - Telekinesis Add-on");
		setLore(List.of("§aThis is an enhanced node.", "Grant's the ability to add", "§7telekinesis on pickaxe's.", " ", "§d§lMYTHIC NODE"));
		setGlowing(true);
		setUnbreakable(true);
	}
}
