package de.knacrack.enhanced_survival.commands.list;

import com.google.common.collect.Lists;
import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStatsOld;
import de.knacrack.enhanced_survival.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class FlyCommand extends CommandConstructor {
    public FlyCommand() {
        super("fly", Messages.PERMISSION_PREFIX.getMessage() + ".fly", Lists.newArrayList());
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if(commandSender.hasPermission(getPermission())) return;

        if (arguments.length == 0 && commandSender instanceof Player player) {
            enableFlying(player);
            return;
        } else if(arguments.length == 1) {
            Player other = Bukkit.getPlayer(arguments[0]);
            if (other == null) {
                commandSender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                return;
            }

            enableFlying(other);

            if (other.getAllowFlight()) {
                commandSender.sendMessage("Flying is now enabled for " + PlayerStats.getCharacter(other).getCustomName() + "!");
            }
            else {
                commandSender.sendMessage("Flying is now disabled for " + PlayerStats.getCharacter(other).getCustomName() + "!");
            }

            return;
        }

        commandSender.sendMessage(Messages.SYNTAX_ERROR.getMessage());

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        List<String> list = Collections.emptyList();

        if (commandSender.hasPermission(getPermission()) && arguments.length == 1) {
            list = getPlayers(arguments[0]);
        }

        return list;
    }

    private void enableFlying(Player player) {
        boolean allowFlight = player.getAllowFlight();
        if (allowFlight) {
            player.sendMessage("Flying is now " + "disabled" + "!");
        } else {
            player.sendMessage("Flying is now " + "enabled" + "!");
        }
        player.setAllowFlight(!allowFlight);
    }

    @Override
    public boolean register() {
        return true;
    }
}
