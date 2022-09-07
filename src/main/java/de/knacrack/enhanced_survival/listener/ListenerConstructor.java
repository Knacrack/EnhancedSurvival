package de.knacrack.enhanced_survival.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.utils.AConstructor;

import java.util.logging.Logger;

public abstract class ListenerConstructor extends AConstructor implements Listener {

    private static final Logger log = Logger.getLogger(ListenerConstructor.class.getSimpleName());

    public ListenerConstructor(String name) {
        super(name);
        registerListener();
    }

    public abstract boolean register();

    private void registerListener() {
        log.info("Register Listener: " + getClass().getSimpleName());

        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }
}
