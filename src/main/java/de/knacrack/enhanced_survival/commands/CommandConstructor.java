package de.knacrack.enhanced_survival.commands;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Lists;

import de.knacrack.enhanced_survival.utils.AConstructor;

public abstract class CommandConstructor extends AConstructor {

    private final String label;

    private final String permission;

    private final List<String> aliases;



    public CommandConstructor(String label, String permission, List<String> aliases) {
        super(label);
        this.label = label;
        this.permission = permission;
        this.aliases = aliases;
    }



    public CommandConstructor(String label, String permission) {
        this(label, permission, Collections.emptyList());
    }



    public CommandConstructor(String label) {
        this(label, null, Collections.emptyList());
    }



    public abstract void performCommand(CommandSender commandSender, String[] arguments);



    public abstract List<String> onTabComplete(CommandSender commandSender, String[] arguments);


    /**
     * @return command's label
     */
    public final String getLabel() {
        return this.label;
    }



    /**
     * @return command's permission entry
     */
    public final String getPermission() {
        return this.permission;
    }



    /**
     * @return command's aliases
     */
    public final List<String> getAliases() {
        return this.aliases;
    }



    /**
         * Get all players on the server
         * @param argument the names of the player
         * @return a list of Strings which contains the name of the players
         */
    public final List<String> getPlayers(String argument) {
        List<Player> players = Bukkit.getOnlinePlayers().stream().filter(player -> player.getName().startsWith(argument)).collect(Collectors.toList());
        List<String> names = Lists.newArrayList();

        if (players.isEmpty())
            return names;

        players.forEach(player -> names.add(player.getName()));
        return names;
    }
}