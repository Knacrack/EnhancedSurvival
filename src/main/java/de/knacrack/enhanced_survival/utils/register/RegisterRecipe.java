package de.knacrack.enhanced_survival.utils.register;

import de.knacrack.enhanced_survival.items.itemapi.IRecipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;

import java.util.logging.Logger;

public class RegisterRecipe implements IRegister{

    private static final Logger log = Logger.getLogger(RegisterRecipe.class.getName());

    private static RegisterRecipe instance = null;

    public static RegisterRecipe getInstance() {
        if(instance == null) {
            instance = new RegisterRecipe();
        }
        return instance;
    }

    @Override
    public void register(Object obj) {
        if (!(obj instanceof IRecipe)) {
            log.info("Can't register this Recipe: " + obj.getClass().getName());
            return;
        }

        log.info("Register Recipe: " + obj.getClass().getName());
        Bukkit.addRecipe(((IRecipe)obj).getRecipe());

    }
}
