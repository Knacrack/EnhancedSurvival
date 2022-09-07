package de.knacrack.enhanced_survival.listener.list;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStatsOld;
import de.knacrack.enhanced_survival.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener extends ListenerConstructor {

    public PlayerChatListener() {
        super("Chat Listener");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().replaceAll("%", "%%");

        PlayerStats stats = PlayerStats.getCharacter(player);
        String name = stats.getCustomName();

        if(player.hasPermission(Messages.PERMISSION_PREFIX.getMessage() + ".chatcolor")) {
            message = ChatColor.translateAlternateColorCodes('&', message);
        }

        event.setFormat(name + "§8: §f" + message);
    }

    @Override
    public boolean register() {
        return true;
    }
}