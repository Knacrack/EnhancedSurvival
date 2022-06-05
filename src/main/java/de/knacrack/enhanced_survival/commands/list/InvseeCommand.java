package de.knacrack.enhanced_survival.commands.list;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.utils.Messages;
import de.knacrack.enhanced_survival.utils.UUIDFetcher;
import de.knacrack.enhanced_survival.utils.Utils;

public class InvseeCommand extends CommandConstructor {

    public InvseeCommand() {
        super("invsee", Messages.PERMISSION_PREFIX.getMessage() + ".invsee", Lists.newArrayList("inv"));
    }



    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if (!Utils.hasPermission(commandSender, getPermission())) {
            return;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Messages.CONSOLE_DO_PLAYER_STUFF.getMessage());
            return;
        }
        if (arguments.length == 1) {

            Player player = Bukkit.getPlayer(arguments[0]);
            UUID uuid = UUIDFetcher.getUUID(arguments[0]);
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            if (!offlinePlayer.hasPlayedBefore()) {
                commandSender.sendMessage("Dieser Spieler war noch nicht auf dem Server!");
                return;
            }

            //            if (offlinePlayer == null) {
            //                commandSender.sendMessage(Messages.PLAYER_NOT_EXIST.getMessage());
            //                return;
            //            }
            //            ((Player)commandSender).openInventory(offlinePlayer.getPlayer().getInventory());
            //            Utils.playSound(((Player)commandSender), Sound.BLOCK_CHEST_OPEN);
        }

    }



    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        return null;
    }

}
