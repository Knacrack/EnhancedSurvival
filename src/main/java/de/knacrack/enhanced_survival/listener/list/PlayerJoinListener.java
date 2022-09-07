package de.knacrack.enhanced_survival.listener.list;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.modules.scoreboard.ServerScoreboard;
import de.knacrack.enhanced_survival.utils.Group;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerJoinListener extends ListenerConstructor {

    public PlayerJoinListener() {
        super("PlayerJoinListener");
    }


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        event.setJoinMessage("§8[§a+§8]§6§l " + event.getPlayer().getName());

        Player player = event.getPlayer();
        PlayerStats stats = PlayerStats.getCharacter(player); // loading values

        player.setPlayerListHeaderFooter("§8\n§r" + "SMP" + "\n§7Version§8: " + "§6" + Main.getInstance().getDescription().getVersion() + "\n§8" + "", "\nJoa, ist halt so da..");
        player.setPlayerListName(stats.getCustomName());

        Group group = stats.getGroup();
        List<String> permissions = group.getPermissions();

        PermissionAttachment permissionAttachment = player.addAttachment(Main.getInstance());
        for (String string : permissions) {
            permissionAttachment.setPermission(string, true);
        }

        PlayerStats.permissionCache.put(player.getUniqueId().toString(), permissionAttachment);

        if (ServerScoreboard.getServerScoreboard() != null) {
            ServerScoreboard.getServerScoreboard().addPlayer(player);
            ServerScoreboard.getServerScoreboard().setScoreboard(player);
        }
    }

    @Override
    public boolean register() {
        return true;
    }
}
