package de.knacrack.enhanced_survival.utils.register;

import de.knacrack.enhanced_survival.commands.CommandConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Logger;

public class RegisterCommand implements IRegister{

    private static final Logger log = Logger.getLogger(RegisterCommand.class.getName());

    private CommandMap commandMap = Bukkit.getCommandMap();

    private static RegisterCommand instance = null;

    public static RegisterCommand getInstance() {
        if(instance == null) {
            instance = new RegisterCommand();
        }
        return instance;
    }

    @Override
    public void register(Object obj) {
        if(!(obj instanceof CommandConstructor)) {
            log.warning("Can't register this Command: " + obj.getClass().getName());
            return;
        }

        log.info("Register Command: " + obj.getClass().getName());

        CommandConstructor cc = (CommandConstructor)obj;

        Command command = new Command(cc.getLabel()) {

            @Override
            public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
                cc.performCommand(commandSender, strings);
                return false;
            }



            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args)
                    throws IllegalArgumentException {
                return cc.onTabComplete(sender, args);
            }
        };

        command.setAliases(cc.getAliases());
        command.setPermission(cc.getPermission());

        commandMap.register("enhanced-survival", command);
    }
}
