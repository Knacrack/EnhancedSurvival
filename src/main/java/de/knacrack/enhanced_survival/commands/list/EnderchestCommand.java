package de.knacrack.enhanced_survival.commands.list;

import com.google.common.collect.Lists;
import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class EnderchestCommand extends CommandConstructor {
    public EnderchestCommand() {
        super("enderchest", Messages.PERMISSION_PREFIX.getMessage() + ".enderchest", Lists.newArrayList("ec"));
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if(commandSender instanceof Player player && player.hasPermission(getPermission())) {
            if(arguments.length == 0){
                player.openInventory(player.getEnderChest());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
            }

            if(arguments.length == 1){
                Player target;
                if((target = Bukkit.getPlayer(arguments[0])) == null) {
                    commandSender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                } else {
                    player.openInventory(target.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
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
