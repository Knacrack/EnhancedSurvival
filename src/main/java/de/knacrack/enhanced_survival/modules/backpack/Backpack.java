package de.knacrack.enhanced_survival.modules.backpack;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import de.knacrack.enhanced_survival.utils.BukkitSerialization;
import de.knacrack.enhanced_survival.utils.InventoryTitleHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import javax.naming.Name;
import java.io.IOException;
import java.util.List;

public class Backpack extends ListenerConstructor {

    public static NamespacedKey namespacedKey = new NamespacedKey(Main.getInstance(), "backpack");

    public ItemStack backpack;

    public Backpack() {
        super("Backpack");
    }

    @Override
    public boolean register() {
        return true;
    }

    @EventHandler
    public void onPlayerCloseBackpack(InventoryCloseEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (item.isSimilar(this.backpack) && isBackpack(item)) {

            event.getPlayer().getInventory().setItemInMainHand(storeInventory(item, event.getView().getTopInventory()));
            this.backpack = new ItemStack(Material.AIR);
        }
    }

    @EventHandler
    public void onPlayerOpenBackpack(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction().isRightClick() && isBackpack(item)) {
            this.backpack = item;
            openBackpack(event.getPlayer(), item);
        }
    }



    @EventHandler
    public void onPlayerMoveItemsInInventory(InventoryClickEvent event) {
        ItemStack clickedItem = event.getWhoClicked().getItemInHand();
        if (isBackpack(clickedItem) && event.getView().title().contains(Component.text("Backpack")) ) event.setCancelled(true);
    }

    public static boolean isBackpack(ItemStack item) {
        return item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(namespacedKey);
    }

    public static ItemStack storeInventory(ItemStack item, Inventory inventory) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, BukkitSerialization.toBase64(inventory));

        item.setItemMeta(meta);
        return item;
    }

    public void openBackpack(Player player, ItemStack item) {
        if (isBackpack(item)) {
            player.openInventory(getInventory(item));
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
        }
    }

    public static Inventory getInventory(ItemStack item) {
        Inventory inventory = null;
        try {
            inventory = BukkitSerialization.fromBase64(item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return inventory;
    }

    public static ItemStack createBackpack(ItemStack item, int rows, String inventoryName) {
        //return storeInventory(item, Bukkit.createInventory(null, rows * 9, "Dödel"));
        return storeInventory(item, Bukkit.createInventory(null, rows * 9, Component.text("Backpack")));
    }


}
