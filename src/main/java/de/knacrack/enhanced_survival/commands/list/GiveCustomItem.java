package de.knacrack.enhanced_survival.commands.list;

import com.google.common.collect.Lists;
import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.logging.Logger;

public class GiveCustomItem extends CommandConstructor {

    private static Logger log = Logger.getLogger(GiveCustomItem.class.getSimpleName());
    public GiveCustomItem() {
        super("give-custom", Messages.PERMISSION_PREFIX.getMessage() + ".give_custom_item", Lists.newArrayList("gc"));
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if (commandSender instanceof Player player && player.hasPermission(getPermission())) {
            if (arguments.length == 1) {
                if (Main.getCustomItemMap().containsKey(arguments[0])) {
                    player.getInventory().addItem(Main.getCustomItemMap().get(arguments[0]).getItem().clone());
                }
            }
        } else {
            commandSender.sendMessage(Messages.ERROR.getMessage());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        List<String> list = Collections.emptyList();
        if (commandSender.hasPermission(getPermission())) {
            if (arguments.length == 1) {
                list = getCustomItems(arguments[0]);
            }
        }
        return list;
    }

    @Override
    public boolean register() {
        return true;
    }
}
