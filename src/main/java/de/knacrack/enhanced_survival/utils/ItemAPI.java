package de.knacrack.enhanced_survival.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

public class ItemAPI {

    private ItemStack itemStack;



    public ItemAPI(ItemStack itemStack) {
        this.itemStack = itemStack;
    }



    public ItemAPI(Material material, int amount) {
        this(new ItemStack(material, amount));
    }



    public ItemAPI(Material material) {
        this(material, 1);
    }



    // basics
    public ItemAPI setName(String displayName) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return this;
    }



    public List<String> getLore() {
        List<String> lore;

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta.hasLore()) {
            lore = itemMeta.getLore();
        }
        else {
            lore = new ArrayList<String>();
        }

        return lore;
    }



    public ItemAPI setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }



    public ItemAPI addLore(String... lines) {
        List<String> lore = getLore();
        lore.addAll(Arrays.asList(lines));
        setLore(lore);
        return this;
    }



    public ItemAPI removeLore(String... lines) {
        List<String> lore = getLore();
        lore.removeAll(Arrays.asList(lines));
        setLore(lore);
        return this;
    }



    public int getCustomModelData() {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta.hasCustomModelData()) {
            return itemMeta.getCustomModelData();
        }

        return 0;
    }



    public ItemAPI setCustomModelData(int data) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(data);
        itemStack.setItemMeta(itemMeta);
        return this;
    }



    public ItemAPI setSkullOwner(String owner) {
        if (itemStack.getType() != Material.PLAYER_HEAD) {
            return this;
        }

        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwner(owner);
        itemStack.setItemMeta(skullMeta);

        return this;
    }



    public ItemAPI setSkullTexture(String base64Texture) {
        if (itemStack.getType() != Material.PLAYER_HEAD) {
            return this;
        }

        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        PlayerProfile playerProfile = Bukkit.createProfile("Notch");
        ProfileProperty profileProperty = new ProfileProperty("", base64Texture);
        playerProfile.setProperty(profileProperty);
        skullMeta.setPlayerProfile(playerProfile);

        itemStack.setItemMeta(skullMeta);
        return this;
    }



    public ItemAPI addEnchantment(Enchantment enchantment, int lvl) {
        itemStack.addUnsafeEnchantment(enchantment, lvl);
        return this;
    }



    public ItemAPI removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }



    public ItemAPI addItemFlags(ItemFlag... flag) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(flag);
        itemStack.setItemMeta(itemMeta);
        return this;
    }



    public ItemAPI isUnbreakable(boolean isUnbreakable) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setUnbreakable(isUnbreakable);
        itemStack.setItemMeta(itemMeta);
        return this;
    }



    public ItemAPI leatherColor(Color color) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta)itemStack.getItemMeta();
        itemMeta.setColor(color);
        itemStack.setItemMeta(itemMeta);
        return this;
    }



    public ItemStack get() {
        return this.itemStack;
    }
}