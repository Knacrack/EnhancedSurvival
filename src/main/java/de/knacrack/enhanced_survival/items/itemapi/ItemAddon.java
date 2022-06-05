package de.knacrack.enhanced_survival.items.itemapi;

import java.util.Arrays;
import java.util.logging.Logger;

import de.knacrack.enhanced_survival.utils.register.RegisterListener;
import de.knacrack.enhanced_survival.utils.register.RegisterRecipe;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.utils.addon.IAddon;

public class ItemAddon implements IAddon {

    private static ItemAddon instance = null;

    private static Logger log = Logger.getLogger(ItemAddon.class.getName());



    public static ItemAddon getInstance() {
        if (instance == null) {
            instance = new ItemAddon();
        }
        return instance;
    }



    @Override
    public void register(Object obj) {
        RegisterRecipe.getInstance().register(obj);
        RegisterListener.getInstance().register(obj);
    }



    @Override
    public void register(Object... obj) {
        Arrays.stream(obj).forEach(o -> register(o));
    }

}
