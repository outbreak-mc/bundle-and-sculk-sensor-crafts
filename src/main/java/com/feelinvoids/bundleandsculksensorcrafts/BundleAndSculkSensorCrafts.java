package com.feelinvoids.bundleandsculksensorcrafts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class BundleAndSculkSensorCrafts extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        Map<String, Material> recipes = new HashMap<>();
        recipes.put("bundle", Material.BUNDLE);
        recipes.put("sculk-sensor", Material.SCULK_SENSOR);

        for (String recipeName : recipes.keySet()) {
            NamespacedKey recipeKey = new NamespacedKey(this, recipeName + "-recipe");
            ItemStack item = new ItemStack(recipes.get(recipeName));

            String[] pattern_parts = getConfig().getString(recipeName+".recipe-pattern").split("\n");
            ShapedRecipe recipe = new ShapedRecipe(recipeKey, item);
            recipe.shape(pattern_parts[0], pattern_parts[1], pattern_parts[2]);
            for (String key : getConfig().getConfigurationSection(recipeName+".materials").getKeys(false)) {
                recipe.setIngredient(key.charAt(0), Material.valueOf(getConfig().getString(recipeName+".materials."+key)));
            }
            Bukkit.addRecipe(recipe);
        }
    }
}
