package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.crafts.CraftingRecipes;
import com.upfault.enhancednodes.nodes.*;
import com.upfault.enhancednodes.utils.MessageChance;
import com.upfault.enhancednodes.utils.OreInfo;
import de.tr7zw.nbtapi.NBT;
import javafx.util.Pair;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.upfault.enhancednodes.listeners.BlockPlaceListener.dataMap;

public class BlockBreakListener implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Material blockType = event.getBlock().getType();
		Player player = event.getPlayer();
		Random random = new Random();

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
				Material.ANCIENT_DEBRIS,
				Material.NETHER_GOLD_ORE,
				Material.NETHER_QUARTZ_ORE,
				Material.STONE
		};

		for (Material allowedMaterial : allowedMaterials) {
			if (blockType == allowedMaterial) {
				OreInfo oreInfo = getOreInfo(allowedMaterial);
				if (oreInfo != null) {
					for (MessageChance messageChance : oreInfo.getMessageChances()) {
						double chance = messageChance.chance();
						if (random.nextDouble() <= chance) {
							player.playSound(player.getLocation(), messageChance.sound(), 1.0F, 1.0F);
							player.sendMessage(messageChance.message());
							player.getWorld().dropItemNaturally(event.getBlock().getLocation(), messageChance.item());
						}
					}
				}
			}
		}
	}


	private OreInfo getOreInfo(Material material) {
		Map<Material, OreInfo> oreInfo = new HashMap<>();
		oreInfo.put(Material.STONE, new OreInfo(
				new MessageChance(1.0 / 5, "§6§lWOAH! §eYou found a §a§lMysterious Fragment§e!", Sound.BLOCK_CHAIN_HIT, new MysteriousFragment().createItemWithoutNBT())

		));
		oreInfo.put(Material.COPPER_ORE, new OreInfo(
				new MessageChance(1.0 / 15750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 17000, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_COPPER_ORE, new OreInfo(
				new MessageChance(1.0 / 15000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 17500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.LAPIS_ORE, new OreInfo(
				new MessageChance(1.0 / 14000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 16000, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_LAPIS_ORE, new OreInfo(
				new MessageChance(1.0 / 13500, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 14500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.COAL_ORE, new OreInfo(
				new MessageChance(1.0 / 8000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_COAL_ORE, new OreInfo(
				new MessageChance(1.0 / 7750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9250, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.IRON_ORE, new OreInfo(
				new MessageChance(1.0 / 8000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_IRON_ORE, new OreInfo(
				new MessageChance(1.0 / 7750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9250, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.GOLD_ORE, new OreInfo(
				new MessageChance(1.0 / 9000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9750, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_GOLD_ORE, new OreInfo(
				new MessageChance(1.0 / 8750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9650, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.NETHER_GOLD_ORE, new OreInfo(
				new MessageChance(1.0 / 9275, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9985, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.NETHER_QUARTZ_ORE, new OreInfo(
				new MessageChance(1.0 / 9275, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 9985, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.REDSTONE_ORE, new OreInfo(
				new MessageChance(1.0 / 10000, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 10500, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_REDSTONE_ORE, new OreInfo(
				new MessageChance(1.0 / 9750, "§6§lWOAH! §eYou found a §7§lCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new CommonNode().createItem()),
				new MessageChance(1.0 / 10250, "§6§lWOAH! §eYou found a §a§lUNCOMMON §enode!", Sound.BLOCK_NOTE_BLOCK_CHIME, new UncommonNode().createItem())
		));
		oreInfo.put(Material.DIAMOND_ORE, new OreInfo(
				new MessageChance(1.0 / 5500, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES, new RareNode().createItem()),
				new MessageChance(1.0 / 6500, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP, new EpicNode().createItem()),
				new MessageChance(1.0 / 7500, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW, new LegendaryNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_DIAMOND_ORE, new OreInfo(
				new MessageChance(1.0 / 5250, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES, new RareNode().createItem()),
				new MessageChance(1.0 / 6250, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP, new EpicNode().createItem()),
				new MessageChance(1.0 / 7250, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW, new LegendaryNode().createItem())
		));
		oreInfo.put(Material.EMERALD_ORE, new OreInfo(
				new MessageChance(1.0 / 2500, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES, new RareNode().createItem()),
				new MessageChance(1.0 / 3500, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP, new EpicNode().createItem()),
				new MessageChance(1.0 / 5000, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW, new LegendaryNode().createItem())
		));
		oreInfo.put(Material.DEEPSLATE_EMERALD_ORE, new OreInfo(
				new MessageChance(1.0 / 2250, "§6§lWOAH! §eYou found a §9§lRARE §enode!", Sound.ENTITY_VILLAGER_YES, new RareNode().createItem()),
				new MessageChance(1.0 / 3250, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP, new EpicNode().createItem()),
				new MessageChance(1.0 / 4000, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW, new LegendaryNode().createItem()),
				new MessageChance(1.0 / 6000, "§c§lCRAZY RARE DROP! §eYou found a §5§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW, new MythicTKNode().createItem()),
				new MessageChance(1.0 / 6000, "§5§lINSANELY RARE DROP! §eYou found a §d§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW, new MythicSTNode().createItem())
		));
		oreInfo.put(Material.ANCIENT_DEBRIS, new OreInfo(
				new MessageChance(1.0 / 100, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP, new EpicNode().createItem()),
				new MessageChance(1.0 / 250, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW, new LegendaryNode().createItem()),
				new MessageChance(1.0 / 1000, "§c§lCRAZY RARE DROP! §eYou found a §5§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW, new MythicTKNode().createItem()),
				new MessageChance(1.0 / 1000, "§5§lINSANELY RARE DROP! §eYou found a §d§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW, new MythicSTNode().createItem())
		));

		return oreInfo.get(material);
	}

	@EventHandler
	public void onForgeBreak(BlockBreakEvent event) {
		Location blockLocation = event.getBlock().getLocation();
		Pair<String, Long> storedData = dataMap.get(blockLocation);

		if(!(event.getBlock().getType() == Material.SMITHING_TABLE)) return;

		if (storedData != null) {
			ItemStack newForgeNode = new NodeForge().createItem();

			NBT.modify(newForgeNode, nbt -> {
				nbt.setString("en_identifier", storedData.getKey());
				nbt.setLong("en_time_created", storedData.getValue());
			});

			event.setDropItems(false);
			if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
				event.getBlock().getWorld().dropItemNaturally(blockLocation, newForgeNode);
			}
			dataMap.remove(blockLocation);
		}
	}
}