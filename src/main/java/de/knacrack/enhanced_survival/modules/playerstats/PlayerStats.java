package de.knacrack.enhanced_survival.modules.playerstats;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.utils.Group;
import de.knacrack.enhanced_survival.utils.UUIDFetcher;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class PlayerStats {

    //Definition of user stats
    private final String uuid;

    private Group group = Group.PLAYER;

    public String getUuid() {
        return uuid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    private double coins = 0d;

    private double maxHealth = 20d;

    private long experience = 0l;

    //Class variables
    private static HashMap<String, PlayerStats> cache = new HashMap<>();

    public static final HashMap<String, PermissionAttachment> permissionCache = new HashMap<>();

    public static Collection<PlayerStats> getAllStats() {
        return cache.values();
    }

    private static final int MAX_LEVEL = 100;

    private static final long MAX_XP = 10000L;

    private static final long A = MAX_XP / MAX_LEVEL;

    public PlayerStats(@NotNull String uuid) {
        this.uuid = uuid;
    }

    public static PlayerStats getCharacter(@NotNull String uuid) {
        PlayerStats stats;
        if (!cache.containsKey(uuid)) {
            stats = new PlayerStats(uuid).load();
            cache.put(uuid, stats);
        } else {
            stats = cache.get(uuid);
        }
        return stats;
    }

    public static PlayerStats getCharacter(@NotNull UUID uuid) {
        return getCharacter(uuid.toString());
    }

    public static PlayerStats getCharacter(@NotNull OfflinePlayer player) {
        return getCharacter(player.getUniqueId().toString());
    }

    public static PlayerStats getCharacter(@NotNull CommandSender player) {
        return getCharacter(((Player) player).getUniqueId().toString());
    }

    public static PlayerStats getCharacter(@NotNull Player player) {
        return getCharacter(player.getUniqueId().toString());
    }

    private String getName() {
        UUID uniqueId = UUID.fromString(uuid);

        Player player = Bukkit.getPlayer(uniqueId);
        if(player != null) return player.getName();

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uniqueId);
        if(offlinePlayer.getName() != null) return offlinePlayer.getName();

        return UUIDFetcher.getName(uniqueId);
    }

    public String getCustomName() {
        return "§r" + getGroup().getPrefix() + getName() + "§r" + getGroup().getSuffix();
    }

    public static File getDirectory() {
        return new File(Main.getInstance().getDataFolder().getPath() + "/player/");
    }

    private File getFile() {
        File file = getDirectory();
        file.mkdirs();
        file = new File(file.getPath(), this.uuid + ".json");
        return file;
    }

    @SneakyThrows
    public void save() {
        File file = getFile();
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);
        Main.GSON.toJson(this, writer);
        writer.flush();
        writer.close();
    }

    @SneakyThrows
    private PlayerStats load() {
        PlayerStats stats = this;
        File file = getFile();
        if (file.exists()) {
            FileReader reader = new FileReader(file);
            stats = Main.GSON.fromJson(reader, getClass());
            reader.close();
        } else {
            save();
        }
        return stats;

    }

    public static void dispose(@NotNull String uuid) {
        PlayerStats stats = cache.getOrDefault(uuid, null);

        if (stats != null) {
            stats.save();
        }

        cache.remove(uuid);
        permissionCache.remove(uuid);
        //Bukkit.getConsoleSender().sendMessage("Cache size: " + cache.size());
    }

    public void addCoins(double coins) {
        setCoins(this.coins + coins);
    }

    public void removeCoins(double coins) {
        double amount = this.coins - coins;
        if (amount < 0d) {
            amount = 0d;
        }
        setCoins(amount);
    }

    public boolean hasEnoughCoins(double coins) {
        return coins > 0d && coins >= this.coins;
    }

    public void resetCoins() {
        this.coins = 0d;
    }

    public void setExperience(long amount) {
        if (!(amount < 0l)) {
            this.experience = amount;
        }
    }

    public void addExperience(long amount) {
        setExperience(this.experience + amount);
    }

    public void removeExperience(long amount) {
        setExperience((this.experience - amount) < 0l ? 0l : this.experience - amount);
    }

    public void resetExperience() {
        this.experience = 0l;
    }

    public boolean hasEnoughXP(long amount) {
        return amount > 0l && this.experience >= amount;
    }

    public int getLevel() {
        int lvl = 0;
        long xpNeeded = 0l;

        while (xpNeeded < this.experience || (lvl <= MAX_LEVEL)) {
            lvl++;
            xpNeeded = (A * lvl) * lvl;
        }
        return lvl;
    }

    public boolean canLevelUp() {
        return getLevel() < MAX_LEVEL;
    }

    public long getRequiredExperience(int level) {
        long req = 0l;
        if (level != MAX_LEVEL) {
            req = (A * level) * level;
        }
        return req;
    }

    public long getCurrentExperience() {
        return this.experience - getRequiredExperience(getLevel());
    }

}
