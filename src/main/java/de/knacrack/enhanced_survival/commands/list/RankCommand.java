package de.knacrack.enhanced_survival.commands.list;

import com.google.common.collect.Lists;
import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;
import de.knacrack.enhanced_survival.modules.scoreboard.ServerScoreboard;
import de.knacrack.enhanced_survival.utils.Group;
import de.knacrack.enhanced_survival.utils.Messages;
import de.knacrack.enhanced_survival.utils.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.*;

public class RankCommand extends CommandConstructor {

    public RankCommand() {
        super("rank", Messages.PERMISSION_PREFIX.getMessage() + ".rank", Lists.newArrayList("group"));
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if(!(commandSender instanceof Player sender)) {
            return;
        }

        if (!sender.hasPermission(getPermission())) {
            return;
        }

        if(arguments.length == 2) {
            UUID uuid = UUIDFetcher.getUUID(arguments[0]);
            if(uuid == null) {
                commandSender.sendMessage(Messages.PLAYER_NOT_EXIST.getMessage());
                return;
            }

            Group group;
            try {
                group = Group.valueOf(arguments[1].toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception) {
                commandSender.sendMessage("This group doesn't exist.");
                return;
            }

            PlayerStats stats = PlayerStats.getCharacter(uuid);
            stats.setGroup(group);
            commandSender.sendMessage("You've changed to group from " + UUIDFetcher.getName(uuid) + " to " + group.name());

            Player player = Bukkit.getPlayer(uuid);
            if(player != null) {
                ServerScoreboard.getServerScoreboard().removePlayer(player);
                ServerScoreboard.getServerScoreboard().addPlayer(player);
                player.setPlayerListName(stats.getCustomName());

                PermissionAttachment permissionAttachment = PlayerStats.permissionCache.get(player.getUniqueId().toString());
                player.removeAttachment(permissionAttachment);

                permissionAttachment = player.addAttachment(Main.getInstance());
                for(String string : group.getPermissions()) {
                    permissionAttachment.setPermission(string, true);
                }

                PlayerStats.permissionCache.put(player.getUniqueId().toString(), permissionAttachment);
            }
            return;
        }

        sender.sendMessage(Messages.ERROR.getMessage());

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        if(!commandSender.hasPermission(getPermission())) return Collections.emptyList();

        if(arguments.length == 1) {
            return getPlayers(arguments[0]);
        } else if(arguments.length == 2) {
            List<String> groups = new ArrayList<>();
            Arrays.stream(Group.values()).filter(group -> group.name().startsWith(arguments[1].toUpperCase(Locale.ROOT))).forEach(group -> groups.add(group.name()));
            return groups;
        }

        return Collections.emptyList();
    }

    @Override
    public boolean register() {
        return true;
    }
}