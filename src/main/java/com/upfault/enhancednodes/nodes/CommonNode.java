package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class CommonNode  extends NodeBase {

	public CommonNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§7Common Node - Efficiency Upgrade");
		setLore(List.of("§aThis is an enhanced node.", " ", "§7Grant's the ability to upgrade", "§7efficiency on pickaxe's.", " ", "§7§lCOMMON NODE"));
		setGlowing(true);
		setUnbreakable(true);
	}
}
