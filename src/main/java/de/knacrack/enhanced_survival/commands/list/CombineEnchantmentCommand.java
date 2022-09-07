package de.knacrack.enhanced_survival.commands.list;

import com.google.common.collect.Lists;
import de.knacrack.enhanced_survival.commands.CommandConstructor;
import de.knacrack.enhanced_survival.utils.Messages;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class CombineEnchantmentCommand extends CommandConstructor {
    public CombineEnchantmentCommand() {
        super("combine-enchantment", Messages.PERMISSION_PREFIX.getMessage() + ".combineenchantment", Lists.newArrayList("ce"));
    }

    @Override
    public void performCommand(CommandSender commandSender, String[] arguments) {
        if (commandSender instanceof Player player && player.hasPermission(getPermission())) {
            ItemStack leftHand = player.getInventory().getItem(EquipmentSlot.OFF_HAND);
            ItemStack rightHand = player.getInventory().getItem(EquipmentSlot.HAND);

            if (Material.AIR.equals(leftHand.getType()) || Material.AIR.equals(rightHand.getType())) {
                commandSender.sendMessage("Du muss in beiden Händen Items Tragen");
            } else if (Material.ENCHANTED_BOOK.equals(leftHand.getType()) && Material.ENCHANTED_BOOK.equals(rightHand.getType())) {
                EnchantmentStorageMeta leftMeta = (EnchantmentStorageMeta) leftHand.getItemMeta();
                EnchantmentStorageMeta rightMeta = (EnchantmentStorageMeta) rightHand.getItemMeta();

                for (Map.Entry<Enchantment, Integer> entry : leftMeta.getStoredEnchants().entrySet()) {
                    rightMeta.addStoredEnchant(entry.getKey(), entry.getValue(), true);
                }
                rightHand.setItemMeta(rightMeta);
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, new ItemStack(Material.AIR));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);
            } else if (Material.ENCHANTED_BOOK.equals(leftHand.getType()) && !Material.AIR.equals(rightHand.getType())) {
                EnchantmentStorageMeta leftMeta = (EnchantmentStorageMeta) leftHand.getItemMeta();
                rightHand.addUnsafeEnchantments(leftMeta.getStoredEnchants());
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, new ItemStack(Material.AIR));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);
            } else if (!Material.AIR.equals(leftHand.getType()) && Material.ENCHANTED_BOOK.equals(rightHand.getType())) {
                EnchantmentStorageMeta rightMeta = (EnchantmentStorageMeta) rightHand.getItemMeta();
                for (Map.Entry<Enchantment, Integer> entry : leftHand.getEnchantments().entrySet()) {
                    rightMeta.addStoredEnchant(entry.getKey(), entry.getValue(), true);
                }
                rightHand.setItemMeta(rightMeta);
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, removeAllEnchantments(leftHand));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);
                //    EnchantmentStorageMeta rightMeta = (EnchantmentStorageMeta)leftHand.getItemMeta();
                //    leftHand.addUnsafeEnchantments(rightMeta.getStoredEnchants());
                //    player.getInventory().setItem(EquipmentSlot.HAND, new ItemStack(Material.AIR));
                //    player.getInventory().setItem(EquipmentSlot.OFF_HAND, leftHand );

            } else if (!Material.AIR.equals(leftHand.getType()) && !Material.AIR.equals(rightHand.getType())) {
                rightHand.addUnsafeEnchantments(leftHand.getEnchantments());
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, removeAllEnchantments(leftHand));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);
            } else {
                commandSender.sendMessage("something went wrong! ¯\\_(ツ)_/¯");
            }


        } else {
            commandSender.sendMessage(Messages.ERROR.getMessage());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] arguments) {
        return null;
    }

    @Override
    public boolean register() {
        return true;
    }

    private ItemStack removeAllEnchantments(ItemStack item) {
        for (Map.Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
            item.removeEnchantment(entry.getKey());
        }
        return item;
    }

}
