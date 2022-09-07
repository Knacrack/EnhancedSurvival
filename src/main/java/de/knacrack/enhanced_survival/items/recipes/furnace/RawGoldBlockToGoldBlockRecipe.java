package de.knacrack.enhanced_survival.items.recipes.furnace;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.IRecipe;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

public class RawGoldBlockToGoldBlockRecipe implements Keyed, IRecipe {
    @Override
    public Recipe getRecipe() {
        return new FurnaceRecipe(getKey(), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 9, 200);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), getClass().getSimpleName());
    }
}
