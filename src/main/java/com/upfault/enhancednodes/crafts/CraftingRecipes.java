package com.upfault.enhancednodes.crafts;

import com.upfault.enhancednodes.nodes.MysteriousFragment;
import com.upfault.enhancednodes.nodes.NodeForge;
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

		ShapedRecipe recipe = new ShapedRecipe(key, new NodeForge().createItem());
		recipe.shape(
				"XOX",
				"OIO",
				"XOX"
		);
		recipe.setIngredient('X', new MysteriousFragment().createItemWithoutNBT());
		recipe.setIngredient('O', new RecipeChoice.MaterialChoice(Material.OAK_PLANKS, Material.ACACIA_PLANKS, Material.BIRCH_PLANKS, Material.DARK_OAK_PLANKS, Material.JUNGLE_PLANKS, Material.SPRUCE_PLANKS, Material.MANGROVE_PLANKS, Material.CRIMSON_PLANKS, Material.WARPED_PLANKS));
		recipe.setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));

		plugin.getServer().addRecipe(recipe);
	}
}

