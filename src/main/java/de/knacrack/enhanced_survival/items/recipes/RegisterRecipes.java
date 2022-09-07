package de.knacrack.enhanced_survival.items.recipes;

import de.knacrack.enhanced_survival.items.IRecipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;

public class RegisterRecipes {

    public RegisterRecipes(Recipe recipe) {
        Bukkit.addRecipe(recipe);
    }

    public RegisterRecipes(IRecipe... recipe) {
        for (IRecipe r : recipe) {
            new RegisterRecipes(r.getRecipe());
        }
    }
}
