package de.knacrack.enhanced_survival.items;

import org.bukkit.Keyed;
import org.bukkit.inventory.ItemStack;

public interface ICustomItem extends Keyed {

    public ItemStack getItem();

    public int getId();

    public String getItemName();

}
