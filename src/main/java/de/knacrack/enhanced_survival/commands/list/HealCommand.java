package de.knacrack.enhanced_survival.commands.list;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.utils.Messages;
import de.knacrack.enhanced_survival.utils.Utils;

public class HealCommand extends CommandConstructor {

    public HealCommand() {
        super("heal", Messages.PERMISSION_PREFIX.getMessage() + ".heal");
    }



    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if (!Utils.hasPermission(commandSender, getPermission()))
            return;

        switch (arguments.length) {
            case 0 -> {
                if (!(commandSender instanceof Player player))
                    return;
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                player.setSaturation(20f);
                player.sendMessage(Messages.SELF_HEALING.getMessage());
                return;
            }

            case 1 -> {
                Player target = Bukkit.getPlayer(arguments[0]);
                if (target == null) {
                    commandSender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                    return;
                }

                target.setHealth(target.getMaxHealth());
                target.setFoodLevel(20);
                target.setSaturation(20f);
                commandSender.sendMessage(Messages.OTHER_HEALING.getMessage().replace("%p%", target.getName()));
                target.sendMessage(Messages.SELF_HEALING.getMessage());

                return;
            }
        }

        commandSender.sendMessage("/heal <player>");
        return;

    }



    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        if (!commandSender.hasPermission(getPermission()))
            return Collections.emptyList();

        if (arguments.length == 1) {
            return getPlayers(arguments[0]);
        }

        return Collections.emptyList();
    }

}
