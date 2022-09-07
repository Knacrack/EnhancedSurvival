package de.knacrack.enhanced_survival.modules.playerstats;

import de.knacrack.enhanced_survival.utils.Group;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class Stats {

    @Getter
    @Setter
    private String uuid;

    @Getter
    @Setter
    private Group group;

    @Getter
    @Setter
    private double coins;

    @Getter
    @Setter
    private double maxHealth;

    public Stats(@NotNull String uuid, @NotNull Group group, @NotNull double coins, @NotNull double maxHealth) {
        this.uuid = uuid;
        this.group = group;
        this.coins = coins;
        this.maxHealth = maxHealth;
    }

    public Stats(String uuid) {
        this.uuid = uuid;
        this.group = Group.PLAYER;
        this.coins = 0d;
        this.maxHealth = 20d;
    }
}
