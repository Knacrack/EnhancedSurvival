package de.knacrack.enhanced_survival.items.recipes.stonecutter;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.IRecipe;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.jetbrains.annotations.NotNull;

public class DirtToGravelRecipe implements Keyed, IRecipe {
    @Override
    public Recipe getRecipe() {
        return new StonecuttingRecipe(getKey(), new ItemStack(Material.GRAVEL), Material.DIRT);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), getClass().getSimpleName().toLowerCase());
    }
}
