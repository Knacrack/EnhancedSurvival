package de.knacrack.enhanced_survival.items.list;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.ICustomItem;
import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import de.knacrack.enhanced_survival.utils.ItemAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ScrollOfCreation extends ListenerConstructor implements ICustomItem {

    private static ICustomItem item;

    public ScrollOfCreation() {
        super("ScrollOfCreation");
        if (item == null) {
            item = this;
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemAPI(Material.PAPER).setName(ChatColor.of(new Color(85, 37, 255)) + "§l" + getItemName()).setCustomModelData(getId()).get();
    }

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public String getItemName() {
        return "Schriftrolle der Schöpfung";
    }

    @Override
    public boolean register() {
        return true;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), "scroll_of_creation");
    }

    public static ICustomItem getCustomItem() {
        if (item == null) {
            item = new ScrollOfCreation();
        }
        return item;
    }

    @EventHandler
    public void onActionCreation(PlayerInteractEvent event) {
        if (event.getAction().isRightClick() && getItem().isSimilar(event.getItem())) {
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                event.setCancelled(true);
                event.getPlayer().openWorkbench(event.getPlayer().getLocation(), true);
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
            }, 4);
        }

    }
}
