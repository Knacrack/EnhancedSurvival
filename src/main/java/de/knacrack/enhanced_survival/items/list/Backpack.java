package de.knacrack.enhanced_survival.items.list;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.ICustomItem;
import de.knacrack.enhanced_survival.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Backpack implements ICustomItem {

    private static ICustomItem item;

    public Backpack() {
        if (item == null) {
            item = this;
        }
    }

    @Override
    public ItemStack getItem() {
        return de.knacrack.enhanced_survival.modules.backpack.Backpack.createBackpack(new ItemAPI(Material.LEATHER).setCustomModelData(getId()).setName(getItemName()).get(), 3, "§6§lBackpack");
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getItemName() {
        return "§6§lBackpack";
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), "backpack");
    }

    public static ICustomItem getCustomItem() {
        if (item == null) {
            item = new Backpack();
        }
        return item;
    }
}
