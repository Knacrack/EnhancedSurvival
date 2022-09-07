package de.knacrack.enhanced_survival.listener.list;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDieListener extends ListenerConstructor {
    public PlayerDieListener() {
        super("PlayerDieListener");
    }

    //PLAYER_DIE("&8[&r☠&8] &c<PLAYER> ist gestorben!"),
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage("§8[§r☠§8] §c" + event.getPlayer().getName());
    }

    @Override
    public boolean register() {
        return true;
    }
}
