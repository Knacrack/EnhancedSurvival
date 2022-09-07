package de.knacrack.enhanced_survival.commands.list;

import com.google.common.collect.Lists;
import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class GameModeCommand extends CommandConstructor {
    public GameModeCommand() {
        super("gamemode", Messages.PERMISSION_PREFIX.getMessage() + ".gamemode", Lists.newArrayList("gm"));
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if (!commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage(Messages.NO_PERMISSION.getMessage());
            return;
        }

        if (commandSender instanceof Player player) {
            GameMode mode;
            if (arguments.length == 1) {
                mode = validate(arguments[0]);

                if(changeGameMode(player, mode)) {
                    commandSender.sendMessage("Du bist jetzt im Spielmodus " + mode + ".");
                } else {
                    commandSender.sendMessage("Du bist bereits im Spielmodus " + mode + ".");
                }
            } else {
                commandSender.sendMessage(Messages.ERROR.getMessage());
            }
        } else {
            GameMode mode;
            Player player;

            if(arguments.length == 2) {
                mode = validate(arguments[0]);
                if((player = Bukkit.getPlayer(arguments[1])) == null){
                    commandSender.sendMessage(Messages.PLAYER_NOT_EXIST.getMessage());
                } else {
                    if (changeGameMode(player, mode)) {
                        commandSender.sendMessage(player.getName() + " ist jetzt im Spielmodus " + mode + ".");
                        player.sendMessage("Du bist jetzt im Spielmodus " + mode + ".");
                    } else {
                        commandSender.sendMessage(player.getName() + " ist bereits im Spielmodus " + mode + ".");
                    }
                }
            } else {
                commandSender.sendMessage(Messages.ERROR.getMessage());
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        List<String> list = Collections.emptyList();
        if (commandSender.hasPermission(getPermission())) {
            if (arguments.length == 1) {
                list = Lists.newArrayList("0", "1", "2", "3");
            } else if (arguments.length == 2) {
                list = getPlayers(arguments[1]);
            }
        }
        return list;
    }

    private GameMode validate(String pPossibility) {
        GameMode mode = GameMode.SURVIVAL;
        switch (pPossibility.toLowerCase()) {
            case "su", "0", "s", "survival" -> mode = GameMode.SURVIVAL;

            case  "cr", "1", "c", "creative" -> mode = GameMode.CREATIVE;

            case "ad", "2", "a", "adventure" -> mode = GameMode.ADVENTURE;

            case "sp", "3", "spec", "spectator" -> mode = GameMode.SPECTATOR;
        }
        return mode;
    }

    private boolean changeGameMode(Player pPlayer, GameMode pGameMode) {
        boolean outVar = false;
        if (!pPlayer.getGameMode().equals(pGameMode)) {
            pPlayer.setGameMode(pGameMode);
            outVar = true;
        }
        return outVar;
    }

    @Override
    public boolean register() {
        return true;
    }
}