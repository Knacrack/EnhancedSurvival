package de.knacrack.enhanced_survival.utils.register;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.logging.Logger;

public class RegisterListener implements IRegister{

    private static final Logger log = Logger.getLogger(RegisterListener.class.getName());

    private static RegisterListener instance = null;

    public static RegisterListener getInstance() {
        if(instance == null) {
            instance = new RegisterListener();
        }
        return instance;
    }

    @Override
    public void register(Object obj) {
        if (!(obj instanceof Listener)) {
            log.warning("Can't register this Listener: " + obj.getClass().getName());
            return;
        }

        log.info("Register Listener: " + obj.getClass().getName());

        Listener listener = (Listener)obj;
        Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
    }
}
