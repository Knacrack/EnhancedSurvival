package de.knacrack.enhancedsurvival;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.knacrack.enhancedsurvival.listener.list.PlayerJoinListener;

public class Main extends JavaPlugin {

    private static Main instance;



    @Override
    public void onEnable() {
        instance = this;
        new PlayerJoinListener();
    }



    @Override
    public void onDisable() {
        Bukkit.getServer().getOnlinePlayers().stream().forEach(p -> p.closeInventory());
    }



    public static Main getInstance() {
        return instance;
    }
}
