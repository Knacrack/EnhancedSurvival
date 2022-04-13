package de.knacrack.enhanced_survival.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.utils.AConstructor;

public class ListenerConstructor extends AConstructor implements Listener {

    public ListenerConstructor(String name) {
        super(name);
    }



    @Override
    public void register() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

}
