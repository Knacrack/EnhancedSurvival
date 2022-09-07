package de.knacrack.enhanced_survival.items.recipes.workbench;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.IRecipe;
import de.knacrack.enhanced_survival.items.list.Backpack;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

public class BackpackRecipe implements Keyed, IRecipe {
    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(getKey(), Backpack.getCustomItem().getItem());
        recipe.shape("LSL", "L L", "LLL");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('L', Material.LEATHER);
        return recipe;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), "backpack_recipe");
    }


}
