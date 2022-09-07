package de.knacrack.enhanced_survival.modules.scoreboard;

import de.knacrack.enhanced_survival.modules.playerstats.PlayerStatsOld;
import de.knacrack.enhanced_survival.utils.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ServerScoreboard {

    private static ServerScoreboard serverScoreboard;

    public static ServerScoreboard getServerScoreboard() {
        return serverScoreboard;
    }

    private Scoreboard scoreboard;
    private Objective objective;

    public ServerScoreboard() {
        serverScoreboard = this;

        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective("a", "b");

        registerGroups();
        addPlayers();
    }

    private void registerGroups() {
        for(Group group : Group.values()) {
            this.scoreboard.registerNewTeam(group.getTablistGroupName());
        }
    }

    private void addPlayers() {
        Bukkit.getOnlinePlayers().forEach(this::addPlayer);
        Bukkit.getOnlinePlayers().forEach(this::setScoreboard);
    }

    public void addPlayer(Player player) {
        Group group = PlayerStatsOld.getCharacter(player).getGroup();
        Team team = this.scoreboard.getTeam(group.getTablistGroupName());

        assert team != null;
        team.addPlayer(player);
    }

    public void removePlayer(Player player) {
        Group group = PlayerStatsOld.getCharacter(player).getGroup();
        Team team = this.scoreboard.getTeam(group.getTablistGroupName());

        assert team != null;
        team.removePlayer(player);
    }

    public void setScoreboard(Player player) {
        player.setScoreboard(this.scoreboard);
    }
}
