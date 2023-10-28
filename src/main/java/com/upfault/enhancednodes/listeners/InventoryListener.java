package com.upfault.enhancednodes.listeners;

import com.upfault.enhancednodes.EnhancedNodes;
import com.upfault.enhancednodes.guis.NodeForgePanel;
import com.upfault.enhancednodes.nodes.MysteriousFragment;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class InventoryListener implements Listener {

	@EventHandler
	public void onMenuExit(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		UUID playerId = player.getUniqueId();;


		if (NodeForgePanel.playerInventories.containsKey(playerId)) {

			Inventory forgeMenu = NodeForgePanel.playerInventories.get(player.getUniqueId());
			ItemStack pickaxe = forgeMenu.getItem(19);
			ItemStack node = forgeMenu.getItem(25);
			ItemStack combinedItem = forgeMenu.getItem(31);

			returnItemsOnClose(player, pickaxe, node, combinedItem);
			NodeForgePanel.playerInventories.remove(playerId);
		}
	}

	private void returnItemsOnClose(Player player, ItemStack pickaxe, ItemStack node, ItemStack combinedItem) {
		if (pickaxe != null) {
			returnItemToPlayer(player, pickaxe);
		}

		if (combinedItem != null && !combinedItem.equals(createItem(Material.BARRIER, "§eNode Forge", "§7Place a target item in the left", "§7slot and a sacrifice node in the", "§7right slot to add the node's", "§7effects to the item."))) {
			returnItemToPlayer(player, combinedItem);
		}

		if (node != null) {
			returnItemToPlayer(player, node);
		}
	}

	private void returnItemToPlayer(Player player, ItemStack item) {
		if (player.getInventory().firstEmpty() == -1) {
			player.getWorld().dropItemNaturally(player.getLocation(), item.clone());
		} else {
			player.getInventory().addItem(item.clone());
		}
	}

	@EventHandler
	private void onClick(InventoryClickEvent event) {
		Inventory clickedInventory = event.getClickedInventory();
		Player player = (Player) event.getWhoClicked();
		int clickedSlot = event.getSlot();
		ItemStack clickedItem = event.getCurrentItem();
		Inventory forgeMenu = NodeForgePanel.playerInventories.get(player.getUniqueId());
		if (clickedSlot == InventoryView.OUTSIDE || clickedInventory == null || clickedItem == null) {
			return;
		}

		if (NodeForgePanel.playerInventories.containsKey(player.getUniqueId())) {
			event.setCancelled(true);
			if (clickedInventory == forgeMenu) {
				if (clickedSlot == 19 && forgeMenu.getItem(19) != null) {
					ItemStack itemToMove = forgeMenu.getItem(19);
					if (player.getInventory().firstEmpty() == -1) {
						assert itemToMove != null;
						player.getWorld().dropItemNaturally(player.getLocation(), itemToMove);
					} else {
						assert itemToMove != null;
						player.getInventory().addItem(itemToMove);
						player.sendMessage("§aRetrieved Pickaxe!");
					}
					forgeMenu.setItem(19, null);
				} else if (clickedSlot == 25 && forgeMenu.getItem(25) != null) {
					ItemStack itemToMove = forgeMenu.getItem(25);
					if (player.getInventory().firstEmpty() == -1) {
						assert itemToMove != null;
						player.getWorld().dropItemNaturally(player.getLocation(), itemToMove);
					} else {
						assert itemToMove != null;
						player.getInventory().addItem(itemToMove);
						player.sendMessage("§aRetrieved Node!");
					}
					forgeMenu.setItem(25, null);
				} else if (clickedSlot == 31 && !Objects.equals(forgeMenu.getItem(31), createItem(Material.BARRIER, "§eNode Forge", "§7Place a target item in the left", "§7slot and a sacrifice node in the", "§7right slot to add the node's", "§7effects to the item."))) {
					if (player.getInventory().firstEmpty() == -1) {
						player.getWorld().dropItemNaturally(player.getLocation(), clickedItem);
						forgeMenu.setItem(31, createItem(Material.BARRIER, "§eNode Forge", "§7Place a target item in the left", "§7slot and a sacrifice node in the", "§7right slot to add the node's", "§7effects to the item."));
					} else {
						player.getInventory().addItem(clickedItem);
						forgeMenu.setItem(31, createItem(Material.BARRIER, "§eNode Forge", "§7Place a target item in the left", "§7slot and a sacrifice node in the", "§7right slot to add the node's", "§7effects to the item."));
					}
				} else if (clickedSlot == 13) {
					if (forgeMenu.getItem(19) != null && forgeMenu.getItem(25) != null) {
						NodeForgePanel.combiningNode.put(player.getUniqueId(), true);
						BukkitRunnable rainbowAnimation = getAnimation(player);
						rainbowAnimation.runTaskTimer(EnhancedNodes.getInstance(), 0, 0);
					} else {
						player.sendMessage("§cPlace both a pickaxe and a node to proceed with the upgrade!");
					}
				} else {
					if (NodeForgePanel.combiningNode.containsKey(player.getUniqueId()) && NodeForgePanel.combiningNode.get(player.getUniqueId())) {
						player.sendMessage("§cYou are already combining a node!");
						event.setCancelled(true);
					}
				}
			} else if (clickedInventory instanceof PlayerInventory) {
				if (isPickaxe(clickedItem) && forgeMenu.getItem(19) != null) {
					player.sendMessage("§cYou already have a pickaxe placed!");
					event.setCancelled(true);
				} else if (isPickaxe(clickedItem) && forgeMenu.getItem(19) == null) {
					forgeMenu.setItem(19, clickedItem);
					player.getInventory().setItem(clickedSlot, null);
				}

				if (isNode(clickedItem) && forgeMenu.getItem(25) != null) {
					player.sendMessage("§cYou already have a node placed!");
					event.setCancelled(true);
				} else if (isNode(clickedItem) && forgeMenu.getItem(25) == null) {
					forgeMenu.setItem(25, clickedItem);
					player.getInventory().setItem(clickedSlot, null);
				}

				if (NodeForgePanel.playerInventories.containsKey(player.getUniqueId())) {
					if (!isNode(clickedItem) && !isPickaxe(clickedItem)) {
						player.sendMessage("§cYou cannot place this item!");
					}
				}
			}
		}
	}

	@NotNull
	private BukkitRunnable getAnimation(Player player) {
		Inventory forgeMenu = NodeForgePanel.playerInventories.get(player.getUniqueId());
		int[] slotsToAnimate = {10, 11, 12, 14, 15, 16, 22};
		Material[] paneMaterials = {
				Material.WHITE_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE,
				Material.ORANGE_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE,
				Material.PINK_STAINED_GLASS_PANE, Material.PURPLE_STAINED_GLASS_PANE,
				Material.GREEN_STAINED_GLASS_PANE, Material.YELLOW_STAINED_GLASS_PANE
		};

		return new BukkitRunnable() {
			int tick = 0;
			final int duration = 3 * 20;

			@Override
			public void run() {
				if (tick >= duration || !NodeForgePanel.playerInventories.containsKey(player.getUniqueId())) {
					cleanUpAndCancel();
					return;
				}

				for (int slot : slotsToAnimate) {
					Material paneMaterial = paneMaterials[new Random().nextInt(paneMaterials.length)];
					ItemStack glassPane = createGlassPane(paneMaterial);
					forgeMenu.setItem(slot, glassPane);
				}
				tick++;
			}

			private void cleanUpAndCancel() {
				NodeForgePanel.combiningNode.remove(player.getUniqueId());

				setForgeItems();

				ItemStack product = forgeMenu.getItem(25);
				if (product == null) {
					cancel();
					return;
				}

				if(player.getOpenInventory().getTopInventory() != NodeForgePanel.playerInventories.get(player.getUniqueId())) {
					cancel();
				} else {
					evaluateForge(product);
					cancel();
					return;
				}
			}

			private void setForgeItems() {
				ItemStack upgradeItem = createItem(Material.WHITE_STAINED_GLASS_PANE, "§6Item to Upgrade", "§7The pickaxe you want to upgrade should", "§7be placed in the slot on this side.");
				ItemStack sacrificeItem = createItem(Material.WHITE_STAINED_GLASS_PANE, "§6Node to Sacrifice", "§7The node you're sacrificing in", "§7order to upgrade the item on the", "§7left should be placed in the", "§7slot on this side.");

				for (int slot : new int[]{10, 11, 12}) {
					forgeMenu.setItem(slot, upgradeItem);
				}
				for (int slot : new int[]{14, 15, 16}) {
					forgeMenu.setItem(slot, sacrificeItem);
				}
				forgeMenu.setItem(22, createItem(Material.WHITE_STAINED_GLASS_PANE, "§7 "));
			}

			private void evaluateForge(ItemStack product) {
				ItemMeta meta = product.getItemMeta();

				if (meta != null && meta.hasLore()) {
					List<String> lore = new ArrayList<>(Objects.requireNonNull(meta.getLore()));

					double successChance = extractSuccessChance(lore);

					int randomRoll = new Random().nextInt(100) + 1;

					if (randomRoll <= successChance) {
						forgeSuccessful();
					} else {
						forgeUnsuccessful();
					}
				}
			}

			private double extractSuccessChance(List<String> lore) {
				for (String line : lore) {
					if (line.startsWith("§7Chance of Success: ")) {
						String successPercentage = line.replace("§7Chance of Success: ", "");
						return Double.parseDouble(successPercentage.replaceAll("[^0-9]+", ""));
					}
				}
				return 0.0;
			}

			private void forgeSuccessful() {
				forgeMenu.setItem(31, NodeForgePanel.getInventory().getItem(19));
				forgeMenu.setItem(19, null);
				forgeMenu.setItem(25, null);
				player.sendMessage("§aYour item was forge successfully!");
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
			}

			private void forgeUnsuccessful() {
				forgeMenu.setItem(31, forgeMenu.getItem(19));
				forgeMenu.setItem(19, null);
				forgeMenu.setItem(25, null);
				player.sendMessage("§cYour item was forged unsuccessfully!");
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
			}

			private ItemStack createGlassPane(Material paneMaterial) {
				ItemStack glassPane = new ItemStack(paneMaterial);
				ItemMeta meta = glassPane.getItemMeta();
				if (meta != null) {
					meta.setDisplayName("§7 ");
					glassPane.setItemMeta(meta);
				}
				return glassPane;
			}
		};
	}

	private ItemStack createItem(Material material, String displayName, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(Arrays.asList(lore));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}

	private boolean isPickaxe(ItemStack item) {
		return item != null && item.getType().name().contains("PICKAXE");
	}

	private boolean isNode(ItemStack item) {
		if (item == null || item.getType() == Material.AIR) {
			return false;
		}

		NBTItem nbtItem = new NBTItem(item);

		if (nbtItem.hasTag("en_isForge") || item.equals(new MysteriousFragment().createItemWithoutNBT())) {
			return false;
		}

		return nbtItem.hasTag("en_identifier") && nbtItem.hasTag("en_time_created");
	}
}
