package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.EnhancedNodes;
import com.upfault.enhancednodes.nodes.*;
import com.upfault.enhancednodes.utils.MessageChance;
import com.upfault.enhancednodes.utils.OreInfo;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
				new MessageChance(1.0 / 650, "§6§lWOAH! §eYou found a §a§lMysterious Fragment§e!", Sound.BLOCK_CHAIN_HIT, new MysteriousFragment().createItemWithoutNBT())

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
				new MessageChance(1.0 / 50, "§5§lCRAZY! §eYou found a §d§lEPIC §enode!", Sound.ENTITY_PLAYER_LEVELUP, new EpicNode().createItem()),
				new MessageChance(1.0 / 250, "§c§lINSANE! §eYou found a §6§lLEGENDARY §enode!", Sound.ENTITY_CAT_PURREOW, new LegendaryNode().createItem()),
				new MessageChance(1.0 / 1000, "§c§lCRAZY RARE DROP! §eYou found a §5§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW, new MythicTKNode().createItem()),
				new MessageChance(1.0 / 1000, "§5§lINSANELY RARE DROP! §eYou found a §d§lMYTHIC §enode!", Sound.ENTITY_CAT_PURREOW, new MythicSTNode().createItem())
		));

		return oreInfo.get(material);
	}

	@EventHandler
	public void onForgeBreak(BlockBreakEvent event) {
		Block block = event.getBlock();

		if (block.getType() == Material.SMITHING_TABLE) {
			PersistentDataContainer dataContainer = block.getChunk().getPersistentDataContainer();
			NamespacedKey keyIdentifier = new NamespacedKey(EnhancedNodes.getInstance(), "en_identifier");
			NamespacedKey keyTimeCreated = new NamespacedKey(EnhancedNodes.getInstance(), "en_time_created");
			NamespacedKey isForgeKey = new NamespacedKey(EnhancedNodes.getInstance(), "en_isForge");

			if (dataContainer.has(keyIdentifier, PersistentDataType.STRING) && dataContainer.has(keyTimeCreated, PersistentDataType.LONG) && dataContainer.has(isForgeKey, PersistentDataType.STRING)) {
				String enIdentifier = dataContainer.get(keyIdentifier, PersistentDataType.STRING);
				Long enTimeCreated = dataContainer.get(keyTimeCreated, PersistentDataType.LONG);
				String isForge = dataContainer.get(isForgeKey, PersistentDataType.STRING);

				if (!dataContainer.isEmpty()) {
					ItemStack newForgeNode = new NodeForge().createItem();

					NBTItem nbtItem = new NBTItem(newForgeNode);
					nbtItem.setString("en_identifier", enIdentifier);
					nbtItem.setLong("en_time_created", enTimeCreated);
					nbtItem.setBoolean("en_isForge", Boolean.valueOf(isForge));

					block.setType(Material.AIR);

					if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
						block.getWorld().dropItemNaturally(block.getLocation(), nbtItem.getItem());
					}
				}
			}
		}
	}

	@EventHandler
	public void onForgeExplode(EntityExplodeEvent event) {
		for (Block block : event.blockList()) {
			EnhancedNodes.getInstance().logInfo(event.blockList().toString());
			if (block.getType() == Material.SMITHING_TABLE) {
				PersistentDataContainer dataContainer = block.getChunk().getPersistentDataContainer();
				NamespacedKey keyIdentifier = new NamespacedKey(EnhancedNodes.getInstance(), "en_identifier");
				NamespacedKey keyTimeCreated = new NamespacedKey(EnhancedNodes.getInstance(), "en_time_created");
				NamespacedKey isForgeKey = new NamespacedKey(EnhancedNodes.getInstance(), "en_isForge");

				if (dataContainer.has(keyIdentifier, PersistentDataType.STRING) && dataContainer.has(keyTimeCreated, PersistentDataType.LONG) && dataContainer.has(isForgeKey, PersistentDataType.STRING)) {
					String enIdentifier = dataContainer.get(keyIdentifier, PersistentDataType.STRING);
					Long enTimeCreated = dataContainer.get(keyTimeCreated, PersistentDataType.LONG);
					String isForge = dataContainer.get(isForgeKey, PersistentDataType.STRING);

					if (!dataContainer.isEmpty()) {
						ItemStack newForgeNode = new NodeForge().createItem();

						NBTItem nbtItem = new NBTItem(newForgeNode);
						nbtItem.setString("en_identifier", enIdentifier);
						nbtItem.setLong("en_time_created", enTimeCreated);
						nbtItem.setBoolean("en_isForge", Boolean.valueOf(isForge));
						block.setType(Material.AIR);

						block.getWorld().dropItemNaturally(block.getLocation(), nbtItem.getItem());
					}
				}
			}
		}
	}
}
