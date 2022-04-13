package de.knacrack.enhanced_survival.listener.list;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;

public class PlayerJoinListener extends ListenerConstructor {

    public PlayerJoinListener() {
        super("PlayerJoinListener");
    }



    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Willkommen auf dem Server " + event.getPlayer().getName() + ".");
    }
}
