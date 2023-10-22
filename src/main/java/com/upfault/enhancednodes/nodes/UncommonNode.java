package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class UncommonNode extends NodeBase {

	public UncommonNode() {
		super(Material.FIREWORK_STAR);
		setCustomName("§aUncommon Node - Efficiency Upgrade");
		setLore(List.of("§aThis is an enhanced node.", " ", "§7Grant's the ability to upgrade", "§7efficiency on pickaxe's.", " ", "§a§lUNCOMMON NODE"));
		setGlowing(true);
		setUnbreakable(true);
	}
}
