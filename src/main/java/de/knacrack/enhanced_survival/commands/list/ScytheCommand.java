package de.knacrack.enhanced_survival.commands.list;

import java.util.List;

import de.knacrack.enhanced_survival.items.list.FruitOfGaia;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.items.list.Scythe;
import de.knacrack.enhanced_survival.utils.Messages;

public class ScytheCommand extends CommandConstructor {

    public ScytheCommand() {
        super("scythe", Messages.PERMISSION_PREFIX + ".scythe");
    }



    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if (commandSender instanceof Player player && player.hasPermission(getPermission())) {
            player.getInventory().addItem(Scythe.get().getItem());
        }

    }



    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        return null;
    }

    @Override
    public boolean register() {
        return true;
    }
}
