package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class NodeForge extends NodeBase {

	public NodeForge() {
		super(Material.SMITHING_TABLE);
		setCustomName("§eNode Forge");
		setLore(List.of("§7Forged with the power of mysterious fragments,", "§7our ancestors used this workbench", "§7to apply §cspecial §7upgrades to their tools.", " ", "§e§lSPECIAL WORKBENCH"));
		setGlowing(false);
		setUnbreakable(true);
	}
}
