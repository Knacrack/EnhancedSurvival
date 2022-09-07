package de.knacrack.enhanced_survival.commands.list;

import com.google.common.collect.Lists;
import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.utils.Messages;
import de.knacrack.enhanced_survival.utils.MoneyFormat;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class MoneyCommand extends CommandConstructor {
    public MoneyCommand() {
        super("money", Messages.PERMISSION_PREFIX.getMessage() + ".money", Lists.newArrayList("balance", "bal", "coins"));
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if (arguments.length == 0 && commandSender instanceof Player player) {
            PlayerStats playerStatsOld = PlayerStats.getCharacter(player);
            player.sendMessage("Your balance§8: " + MoneyFormat.getFormated(playerStatsOld.getCoins()) + " Coins");
            return;
        } else if (arguments.length == 1 && commandSender.hasPermission(getPermission() + ".other")) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(arguments[0]);

            if (!offlinePlayer.hasPlayedBefore()) {
                commandSender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                return;
            }

            PlayerStats stats = PlayerStats.getCharacter(offlinePlayer.getUniqueId());
            commandSender.sendMessage(stats.getCustomName() + "'s balance§8: " + MoneyFormat.getFormated(stats.getCoins()));
            return;
        }

        if (commandSender.hasPermission(getPermission() + ".other")) {
            commandSender.sendMessage("/money <player>");
        } else {
            commandSender.sendMessage("/money");
        }
        return;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        if (arguments.length == 1 && commandSender.hasPermission(getPermission() + ".other")) {
            return getPlayers(arguments[0]);
        }

        return Collections.emptyList();
    }

    @Override
    public boolean register() {
        return true;
    }
}
