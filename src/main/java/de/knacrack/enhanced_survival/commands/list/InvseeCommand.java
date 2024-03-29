package de.knacrack.enhanced_survival.commands.list;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
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
        if(commandSender instanceof Player player && player.hasPermission(getPermission())) {
            if(arguments.length == 0){
                player.openInventory(player.getInventory());
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
            }

            if(arguments.length == 1){
                Player target;
                if((target = Bukkit.getPlayer(arguments[0])) == null) {
                    commandSender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                } else {
                    player.openInventory(target.getInventory());
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
                }
            }

        } else {
            commandSender.sendMessage(Messages.ERROR.getMessage());
        }

    }



    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        List<String> list = Collections.emptyList();
        if (arguments.length == 1 && commandSender.hasPermission(getPermission())) {
            list = getPlayers(arguments[0]);
        }
        return list;
    }

    @Override
    public boolean register() {
        return true;
    }
}
