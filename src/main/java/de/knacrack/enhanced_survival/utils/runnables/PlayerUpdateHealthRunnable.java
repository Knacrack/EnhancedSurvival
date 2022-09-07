package de.knacrack.enhanced_survival.utils.runnables;

import de.knacrack.enhanced_survival.modules.playerstats.PlayerStatsOld;
import de.knacrack.enhanced_survival.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerUpdateHealthRunnable implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getGameMode() != GameMode.ADVENTURE && player.getGameMode() != GameMode.SURVIVAL) continue;
            double maxHealth = PlayerStatsOld.getCharacter(player).getPlayerMaxHealth();
            player.setMaxHealth(maxHealth);

            // TODO: link with resourcepack
            player.sendActionBar("§c" + Utils.round(player.getHealth(), 2) + "§8/§c" + Utils.round(maxHealth, 2) + " §4❤");
        }
    }

}
