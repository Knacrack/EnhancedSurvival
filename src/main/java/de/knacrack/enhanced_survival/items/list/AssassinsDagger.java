package de.knacrack.enhanced_survival.items.list;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.ICustomItem;
import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import de.knacrack.enhanced_survival.utils.ItemAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class AssassinsDagger extends ListenerConstructor implements ICustomItem {

    private static ICustomItem item;

    public AssassinsDagger() {
        super("AssassinsDagger");

        if (item == null) {
            item = this;
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemAPI(Material.IRON_SWORD).setName(ChatColor.of(new Color(85, 37, 255)) + "§l" + getItemName()).setCustomModelData(getId()).get();
    }

    @Override
    public int getId() {
        return 5;
    }

    @Override
    public String getItemName() {
        return "Dolch des Assassinen";
    }

    @Override
    public boolean register() {
        return true;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), "assassins_dagger");
    }

    public static ICustomItem getCustomItem() {
        if (item == null) {
            item = new AssassinsDagger();
        }
        return item;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void playerAttackFromBehind(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            if (player.getInventory().getItemInMainHand().isSimilar(getItem())) {

                Vector attackerDirection = player.getLocation().getDirection();
                Vector victimDirection = event.getEntity().getLocation().getDirection();

                if (attackerDirection.dot(victimDirection) > 0) {
                    //player was backstabbed.
                    event.setDamage(event.getDamage() + 11.);
                }
            }
        }
    }
}
