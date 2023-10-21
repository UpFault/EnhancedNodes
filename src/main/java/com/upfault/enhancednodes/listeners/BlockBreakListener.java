package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.utils.MessageChance;
import com.upfault.enhancednodes.utils.OreInfo;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockBreakListener implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Material blockType = event.getBlock().getType();
		Player player = event.getPlayer();
		double random = Math.random();

		Material[] allowedMaterials = {
				Material.LAPIS_ORE,
				Material.DEEPSLATE_LAPIS_ORE,
				Material.COPPER_ORE,
				Material.DEEPSLATE_COPPER_ORE,
				Material.COAL_ORE,
				Material.DEEPSLATE_COAL_ORE,
				Material.IRON_ORE,
				Material.DEEPSLATE_IRON_ORE,
				Material.GOLD_ORE,
				Material.DEEPSLATE_GOLD_ORE,
				Material.REDSTONE_ORE,
				Material.DEEPSLATE_REDSTONE_ORE,
				Material.DIAMOND_ORE,
				Material.DEEPSLATE_DIAMOND_ORE,
				Material.EMERALD_ORE,
				Material.DEEPSLATE_EMERALD_ORE,
				Material.ANCIENT_DEBRIS
		};

		List<MessageChance> possibleMessages = new ArrayList<>();

		for (Material allowedMaterial : allowedMaterials) {
			if (blockType == allowedMaterial) {
				OreInfo oreInfo = getOreInfo(allowedMaterial);
				if (oreInfo != null) {
					possibleMessages.addAll(oreInfo.getMessageChances());
				}
			}
		}

		if (!possibleMessages.isEmpty()) {
			int randomIndex = (int) (random * possibleMessages.size());
			MessageChance selectedMessage = possibleMessages.get(randomIndex);

			player.playSound(player.getLocation(), selectedMessage.sound(), 1.0F, 1.0F);
			player.sendMessage(selectedMessage.message());
		}
	}


	private OreInfo getOreInfo(Material material) {
		Map<Material, OreInfo> oreInfo = new HashMap<>();
		oreInfo.put(Material.COPPER_ORE, new OreInfo(
				new MessageChance(1.0 / 15750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 17000, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.DEEPSLATE_COPPER_ORE, new OreInfo(
				new MessageChance(1.0 / 15000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 17500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.LAPIS_ORE, new OreInfo(
				new MessageChance(1.0 / 14000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 16000, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.DEEPSLATE_LAPIS_ORE, new OreInfo(
				new MessageChance(1.0 / 13500, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 14500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.COAL_ORE, new OreInfo(
				new MessageChance(1.0 / 8000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 9500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.DEEPSLATE_COAL_ORE, new OreInfo(
				new MessageChance(1.0 / 7750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 9250, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.IRON_ORE, new OreInfo(
				new MessageChance(1.0 / 8000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 9500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.DEEPSLATE_IRON_ORE, new OreInfo(
				new MessageChance(1.0 / 7750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 9250, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.GOLD_ORE, new OreInfo(
				new MessageChance(1.0 / 9000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 9750, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.DEEPSLATE_GOLD_ORE, new OreInfo(
				new MessageChance(1.0 / 8750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 9650, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.REDSTONE_ORE, new OreInfo(
				new MessageChance(1.0 / 10000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 10500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.DEEPSLATE_REDSTONE_ORE, new OreInfo(
				new MessageChance(1.0 / 9750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME),
				new MessageChance(1.0 / 10250, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME)
		));
		oreInfo.put(Material.DIAMOND_ORE, new OreInfo(
				new MessageChance(1.0 / 5500, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES),
				new MessageChance(1.0 / 6500, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP),
				new MessageChance(1.0 / 7500, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW)
		));
		oreInfo.put(Material.DEEPSLATE_DIAMOND_ORE, new OreInfo(
				new MessageChance(1.0 / 5250, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES),
				new MessageChance(1.0 / 6250, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP),
				new MessageChance(1.0 / 7250, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW)
		));
		oreInfo.put(Material.EMERALD_ORE, new OreInfo(
				new MessageChance(1.0 / 2500, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES),
				new MessageChance(1.0 / 3500, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP),
				new MessageChance(1.0 / 5000, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW)
		));
		oreInfo.put(Material.DEEPSLATE_EMERALD_ORE, new OreInfo(
				new MessageChance(1.0 / 2250, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES),
				new MessageChance(1.0 / 3250, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP),
				new MessageChance(1.0 / 6000, "§c§lCRAZY RARE DROP! §eYou found a §5§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW),
				new MessageChance(1.0 / 15000, "§d§lINSANELY RARE DROP! §eYou found a §5§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW)
		));
		oreInfo.put(Material.ANCIENT_DEBRIS, new OreInfo(
				new MessageChance(1.0 / 100, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP),
				new MessageChance(1.0 / 250, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW),
				new MessageChance(1.0 / 500, "§c§lCRAZY RARE DROP! §eYou found a §5§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW),
				new MessageChance(1.0 / 1000, "§d§lINSANELY RARE DROP! §eYou found a §5§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW)
		));

		return oreInfo.get(material);
	}
}
