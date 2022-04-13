package de.knacrack.enhanced_survival.listener.list;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;

public class PlayerQuitListener extends ListenerConstructor {

    public PlayerQuitListener() {
        super("PlayerQuitListener");
    }



    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(event.getPlayer().getDisplayName() + " hat den Server verlassen.");
    }

}
