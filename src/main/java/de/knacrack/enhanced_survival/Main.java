package de.knacrack.enhanced_survival;

import de.knacrack.enhanced_survival.commands.CommandAddon;
import de.knacrack.enhanced_survival.items.itemapi.ItemAddon;
import de.knacrack.enhanced_survival.listener.ListenerAddon;
import de.knacrack.enhanced_survival.listener.list.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.knacrack.enhanced_survival.commands.list.HealCommand;
import de.knacrack.enhanced_survival.commands.list.InvseeCommand;
import de.knacrack.enhanced_survival.commands.list.ScytheCommand;
import de.knacrack.enhanced_survival.items.list.Scythe;
import de.knacrack.enhanced_survival.listener.list.PlayerJoinListener;

public class Main extends JavaPlugin {

    private static Main instance;



    @Override
    public void onEnable() {
        instance = this;

        // Listener
        ListenerAddon.getInstance().register(new PlayerJoinListener(), new PlayerQuitListener());

        // Commands
        CommandAddon.getInstance().register(new HealCommand(), new InvseeCommand(), new ScytheCommand());

        //Items
        ItemAddon.getInstance().register(new Scythe());
    }



    @Override
    public void onDisable() {
        Bukkit.getServer().getOnlinePlayers().stream().forEach(p -> p.closeInventory());
    }



    public static Main getInstance() {
        return instance;
    }
}
