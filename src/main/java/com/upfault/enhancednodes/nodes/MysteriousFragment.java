package com.upfault.enhancednodes.nodes;

import org.bukkit.Material;

import java.util.List;

public class MysteriousFragment extends NodeBase {

	public MysteriousFragment() {
		super(Material.FIREWORK_STAR);
		setCustomName("§aMysterious Fragment");
		setLore(List.of(
				"§7Forged in the annals of history by the greats,",
				"§7this legendary relic was used to craft workbenches",
				"§7of unparalleled power, capable of imbuing tools",
				"§7with ancient enchantments and creating artifacts",
				"§7of extraordinary might.",
				" ",
				"§a§lUNCOMMON FRAGMENT"
		));
		setGlowing(true);
		setUnbreakable(true);
	}
}
