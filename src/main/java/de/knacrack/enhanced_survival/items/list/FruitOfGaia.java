package de.knacrack.enhanced_survival.items.list;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.ICustomItem;
import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import de.knacrack.enhanced_survival.utils.ItemAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class FruitOfGaia extends ListenerConstructor implements ICustomItem {

    private static ICustomItem item;

    public FruitOfGaia() {
        super("FruitOfGaia");
        if(item == null) {
            item = this;
        }

    }


    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), "fruit_of_gaia");
    }

    @Override
    public ItemStack getItem() {
        return new ItemAPI(Material.APPLE).setCustomModelData(getId()).setName(ChatColor.of(new Color(85, 37, 255)) + "§l" + getItemName()).addEnchantment(Enchantment.DURABILITY, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).get();
    }

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public String getItemName() {
        return "Frucht Gaias";
    }

    public static ICustomItem getCustomItem(){
        if(item == null) {
            item = new FruitOfGaia();
        }
        return item;
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().isSimilar(getItem())) {
            event.getPlayer().setFoodLevel(20);
            event.getPlayer().setSaturation(20);
            event.setCancelled(true);
            //event.getPlayer().getInventory().setItemInMainHand(event.getItem());
        }
    }

    @Override
    public boolean register() {
        return true;
    }
}
