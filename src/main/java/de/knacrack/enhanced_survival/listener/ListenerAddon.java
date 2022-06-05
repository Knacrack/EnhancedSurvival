package de.knacrack.enhanced_survival.listener;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.commands.CommandAddon;
import de.knacrack.enhanced_survival.items.itemapi.ItemAddon;
import de.knacrack.enhanced_survival.utils.addon.IAddon;
import de.knacrack.enhanced_survival.utils.register.RegisterListener;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.logging.Logger;

public class ListenerAddon implements IAddon {

    private static Logger log = Logger.getLogger(ListenerAddon.class.getName());

    private static ListenerAddon instance = null;

    public static ListenerAddon getInstance() {
        if (instance == null) {
            instance = new ListenerAddon();
        }
        return instance;
    }

    @Override
    public void register(Object obj) {
        RegisterListener.getInstance().register(obj);
    }



    @Override
    public void register(Object... obj) {
        Arrays.stream(obj).forEach(o -> register(o));
    }
}
