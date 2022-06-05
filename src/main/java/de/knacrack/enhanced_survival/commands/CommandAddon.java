package de.knacrack.enhanced_survival.commands;

import de.knacrack.enhanced_survival.utils.addon.IAddon;
import de.knacrack.enhanced_survival.utils.register.RegisterCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CommandAddon implements IAddon {

    private static final Logger log = Logger.getLogger(CommandAddon.class.getName());

    private static CommandAddon instance = null;

    public static CommandAddon getInstance() {
        if (instance == null) {
            instance = new CommandAddon();
        }
        return instance;
    }

    @Override
    public void register(Object obj) {
        RegisterCommand.getInstance().register(obj);
    }



    @Override
    public void register(Object... obj) {
        Arrays.stream(obj).forEach(o -> register(o));
    }
}
