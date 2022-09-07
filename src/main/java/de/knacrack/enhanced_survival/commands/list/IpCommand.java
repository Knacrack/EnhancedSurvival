package de.knacrack.enhanced_survival.commands.list;

import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IpCommand extends CommandConstructor {

    public IpCommand() {
        super("ip", Messages.PERMISSION_PREFIX.getMessage() + ".ip");
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if(commandSender.hasPermission(getPermission())){
            if(arguments.length == 1){
                Player target = Bukkit.getPlayer(arguments[0]);
                if(target == null) {
                    return;
                } else {
                    commandSender.sendMessage(target.getName() + "'s IP: " + target.getAddress().getAddress().getHostAddress());
                }
            }
        } else {
            commandSender.sendMessage(Messages.NO_PERMISSION.getMessage());
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
