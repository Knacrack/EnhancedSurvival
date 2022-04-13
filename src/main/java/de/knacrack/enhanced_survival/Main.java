package de.knacrack.enhanced_survival;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.knacrack.enhanced_survival.commands.list.HealCommand;
import de.knacrack.enhanced_survival.listener.list.PlayerJoinListener;

public class Main extends JavaPlugin {

    private static Main instance;



    @Override
    public void onEnable() {
        instance = this;

        // Listener
        initListener();

        // Commands
        initCommands();
    }



    @Override
    public void onDisable() {
        Bukkit.getServer().getOnlinePlayers().stream().forEach(p -> p.closeInventory());
    }



    public static Main getInstance() {
        return instance;
    }



    private final void initCommands() {
        new HealCommand().register();
    }



    private final void initListener() {
        new PlayerJoinListener().register();
    }
}
