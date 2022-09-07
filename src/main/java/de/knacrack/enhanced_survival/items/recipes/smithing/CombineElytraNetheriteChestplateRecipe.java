package de.knacrack.enhanced_survival.items.recipes.smithing;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.IRecipe;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

public class CombineElytraNetheriteChestplateRecipe implements Keyed, IRecipe {
    @Override
    public Recipe getRecipe() {
        return null; //new SmithingRecipe(getKey(), new ItemStack(Material.ELYTRA), );
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), getClass().getSimpleName().toLowerCase());
    }
}
