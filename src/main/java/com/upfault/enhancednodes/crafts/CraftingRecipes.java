package com.upfault.enhancednodes.crafts;

import com.upfault.enhancednodes.nodes.MysteriousFragment;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraftingRecipes {

	public static void registerNodeForgeRecipe(JavaPlugin plugin) {
		NamespacedKey key = new NamespacedKey(plugin, "enhancednodes_node_forge");

		ShapedRecipe recipe = new ShapedRecipe(key, createNodeForgeItem());
		recipe.shape(
				"XOX",
				"OIO",
				"XOX"
		);
		recipe.setIngredient('X', new RecipeChoice.ExactChoice(new MysteriousFragment().createItemWithoutNBT()));
		recipe.setIngredient('O', new RecipeChoice.MaterialChoice(Material.OAK_PLANKS, Material.ACACIA_PLANKS, Material.BIRCH_PLANKS, Material.DARK_OAK_PLANKS, Material.JUNGLE_PLANKS, Material.SPRUCE_PLANKS, Material.MANGROVE_PLANKS, Material.CRIMSON_PLANKS, Material.WARPED_PLANKS));
		recipe.setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));

		plugin.getServer().addRecipe(recipe);
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createNodeForgeItem() {
		ItemStack nodeForge = new ItemStack(Material.SMITHING_TABLE);
		ItemMeta nodeForgeMeta = nodeForge.getItemMeta();
		nodeForgeMeta.setDisplayName("§eNode Forge");
		List<String> description = List.of(
				"§7Forged with the power of mysterious fragments,",
				"§7our ancestors used this workbench",
				"§7to apply §cspecial §7upgrades to their tools."
		);
		nodeForgeMeta.setLore(description);
		nodeForgeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		nodeForge.setItemMeta(nodeForgeMeta);

		return nodeForge;
	}
}

