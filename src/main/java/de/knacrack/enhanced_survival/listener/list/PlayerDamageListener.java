package de.knacrack.enhanced_survival.listener.list;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStatsOld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDamageListener extends ListenerConstructor {

    public PlayerDamageListener() {
        super("Player Damage Listener");
    }


    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        double damage = event.getFinalDamage();

        if (entity instanceof Player player) {

            if (player.isDead()) {
                event.setDamage(0);
                return;
            }

            PlayerStats stats = PlayerStats.getCharacter(player);

            if (player.getHealth() - damage < 0) {
                player.setHealth(0);
                return;
            } else {
                player.setHealth(player.getHealth() - damage);
            }

            event.setDamage(0);
        }
    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        PlayerStatsOld playerStatsOld = PlayerStatsOld.getCharacter(player);
        player.setMaxHealth(playerStatsOld.getPlayerMaxHealth());
        player.setHealth(playerStatsOld.getPlayerMaxHealth());
    }

    @Override
    public boolean register() {
        return true;
    }
}
