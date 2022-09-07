package de.knacrack.enhanced_survival.listener.list;

import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStatsOld;
import de.knacrack.enhanced_survival.modules.scoreboard.ServerScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;

public class PlayerQuitListener extends ListenerConstructor {

    public PlayerQuitListener() {
        super("PlayerQuitListener");
    }



    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        event.setQuitMessage("§8[§c-§8]§6§l " + event.getPlayer().getName());
        Player player = event.getPlayer();

        PermissionAttachment permissionAttachment = PlayerStatsOld.permissionCache.get(player.getUniqueId().toString());

        if (permissionAttachment != null) {
            player.removeAttachment(permissionAttachment);
        }

        PlayerStats stats = PlayerStats.getCharacter(player);
        stats.setMaxHealth(player.getMaxHealth());

        //PlayerStatsOld.dispose(player.getUniqueId().toString());
        PlayerStats.dispose(player.getUniqueId().toString());
        ServerScoreboard.getServerScoreboard().removePlayer(player);
    }

    @EventHandler
    public void onPlayerKick(@NotNull PlayerKickEvent event) {
        event.setReason("§8[§c-§8]§6§l " + event.getPlayer().getName() + " | " + event.getReason());
    }

    @Override
    public boolean register() {
        return true;
    }
}
