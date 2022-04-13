package de.knacrack.enhanced_survival.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Utils {

    public static double round(final double value, final int digits) {
        return Math.round(Math.pow(10.0, digits) * value) / Math.pow(10.0, digits);
    }



    public static boolean containsUnicodes(@Nonnull String message) {
        final String a = message;
        message = message.replaceAll("[^a-zA-Z.,#;+|/&%!?=(): 1234567890הצ\"*-@€$]", "");
        return !a.equals(message);
    }



    public static boolean isInventoryFull(@Nonnull Inventory inventory) {
        return inventory.firstEmpty() == -1;
    }



    public static boolean canPickup(@Nonnull Inventory inventory, @Nonnull ItemStack itemStack) {
        return inventory.first(itemStack) == -1;
    }



    public static boolean isTool(Material material) {
        if (material == null)
            return false;

        String materialName = material.name();
        if (materialName.contains("_AXE") || materialName.contains("_PICKAXE") || materialName.contains("_SWORD") || materialName.contains("_HOE")
                || materialName.contains("_SHOVEL"))
            return true;
        return false;
    }



    public static boolean isArmor(Material material) {
        if (material == null)
            return false;

        String materialName = material.name();
        if (materialName.contains("_CHESTPLATE") || materialName.contains("_LEGGINGS") || materialName.contains("_BOOTS") || materialName.contains("_HELMET"))
            return true;
        return false;
    }



    public static boolean isZombie(EntityType entityType) {
        return entityType == EntityType.ZOMBIE || entityType == EntityType.HUSK || entityType == EntityType.DROWNED || entityType == EntityType.ZOGLIN
                || entityType == EntityType.ZOMBIE_HORSE || entityType == EntityType.ZOMBIE_VILLAGER;
    }



    public static boolean isUndead(EntityType entityType) {
        return entityType == EntityType.ZOMBIE || entityType == EntityType.HUSK || entityType == EntityType.DROWNED || entityType == EntityType.ZOGLIN
                || entityType == EntityType.ZOMBIE_HORSE || entityType == EntityType.ZOMBIE_VILLAGER || entityType == EntityType.SKELETON
                || entityType == EntityType.WITHER_SKELETON || entityType == EntityType.WITHER || entityType == EntityType.SKELETON_HORSE;
    }



    public static boolean isSkeleton(EntityType entityType) {
        return entityType == EntityType.SKELETON || entityType == EntityType.WITHER_SKELETON || entityType == EntityType.WITHER
                || entityType == EntityType.SKELETON_HORSE;
    }



    public static boolean isLog(Material pMaterial) {
        return !pMaterial.name().contains("LEGACY") && (pMaterial.name().contains("_LOG") || pMaterial.name().contains("_CRIMSON_STEM")
                || pMaterial.name().contains("_WARPED_STEM") || pMaterial.name().contains("MUSHROOM_STEM"));
    }



    public static boolean isStone(Material pMaterial) {
        return Material.STONE.equals(pMaterial) || Material.COBBLESTONE.equals(pMaterial) || Material.DRIPSTONE_BLOCK.equals(pMaterial)
                || Material.BLACKSTONE.equals(pMaterial) || Material.DEEPSLATE.equals(pMaterial) || Material.COBBLED_DEEPSLATE.equals(pMaterial);
    }



    public static boolean isOre(Material pMaterial) {
        return pMaterial.name().contains("_ORE") && !pMaterial.name().contains("LEGACY");

    }



    /**
     * Try to find an entity on player's eyesight.
     *
     * @param player: target player (who should is performing this action?)
     * @param range: range to search for entities | [range ∈ �? | 0.0 < range <= 1.0]
     * @param offset: This variable controls the fine-tuning of searching for entities. Lower values means higher accuracy and more performance cost. [offset ∈ �? | 0.0 < offset <= 1.0]
     * @param tolerance: How large should be the tolerance spectrum of failing to successfully hit the target? The tolerance should be higher than 0, but 0 is a valid input. [tolerance ∈ �? | tolerance >= 0.0]
     * @param firstInTolerance: Should the first entity hit within tolerance be returned or the closest?
     * @param nearbyEntities: list of entities to check
     * @return
     */
    public static Entity getTarget(Player player, float range, float offset, float tolerance, boolean firstInTolerance, List<Entity> nearbyEntities) {
        // handle invalid inputs
        if (range <= 0f) {
            throw new IllegalArgumentException("range must be larger than 0");
        }

        if (offset == 0f) {
            throw new IllegalArgumentException("offset can not be 0");
        }

        if (tolerance < 0.0f) {
            throw new IllegalArgumentException("tolerance can not be lower than 0");
        }

        if (offset > 1f) {
            throw new IllegalArgumentException("offset can not be larger than 1");
        }

        // check if no entities are given
        if (nearbyEntities == null) {
            return null;
        }

        if (nearbyEntities.isEmpty()) {
            return null;
        }

        // handle negative offset-input
        offset = Math.abs(offset);
        double minDistance = Double.MAX_VALUE;

        Entity target = null;
        Vector vector = player.getEyeLocation().getDirection().clone(), additiveVector = player.getEyeLocation().getDirection().multiply(offset).clone();

        while (range > 0) {
            range -= offset;
            vector.add(additiveVector);

            player.getWorld().spawnParticle(Particle.FLAME, vector.getX(), vector.getY(), vector.getZ(), 1, 0.0, 0.0, 0.0, 0.0, null);

            for (Entity entity : nearbyEntities) {
                Location location = vector.toLocation(player.getWorld());
                location.add(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

                double distance = entity.getLocation().distance(location);
                if (distance < minDistance) {
                    minDistance = distance;
                    target = entity;

                    if (firstInTolerance) {
                        if (minDistance <= tolerance) {
                            return target;
                        }
                    }
                }
            }
        }

        if (minDistance > tolerance) {
            target = null;
        }

        return target;
    }



    private static LinkedHashMap<String, Integer> romanNumerals = new LinkedHashMap<>();
    static {
        romanNumerals.put("M", 1000);
        romanNumerals.put("CM", 900);
        romanNumerals.put("D", 500);
        romanNumerals.put("CD", 400);
        romanNumerals.put("C", 100);
        romanNumerals.put("XC", 90);
        romanNumerals.put("L", 50);
        romanNumerals.put("XL", 40);
        romanNumerals.put("X", 10);
        romanNumerals.put("IX", 9);
        romanNumerals.put("V", 5);
        romanNumerals.put("IV", 4);
        romanNumerals.put("I", 1);
    }



    public static String romanNumerals(int Int) {
        String res = "";
        for (Map.Entry<String, Integer> entry : romanNumerals.entrySet()) {
            int matches = Int / entry.getValue();
            res += entry.getKey().repeat(matches);
            Int = Int % entry.getValue();
        }
        return res;
    }



    public static String repeat(String s, int n) {
        if (s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }



    /**
     * Quicksort algorithm
     *
     * Runtime analytics:
     * worst-case:   O(n * log2(n))
     * average-case: Ω(n * log2(n))
     * best-case:    Θ(n * log2(n))
     *
     * @param array Input
     * @param left left-start index
     * @param right right-start index
     *
     * @throws StackOverflowError when recursion stack is full
     *
     * @return sorted integer array from min to max
     */
    public static int[] quicksort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = array[left];
            int j = partition(array, left, right, pivot);
            quicksort(array, left, j);
            quicksort(array, j + 1, right);
        }
        return array;
    }



    private static int partition(int[] array, int left, int right, int pivot) {
        int i = left, j = right;

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = array[i];

                i++;
                j--;
            }
        }

        return Math.max(j, left);
    }



    public static boolean hasPermission(Player player, String permission) {
        boolean state = player.hasPermission(permission);

        if (!state) {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
        }

        return state;
    }



    public static boolean hasPermission(CommandSender player, String permission) {
        boolean state = player.hasPermission(permission);

        if (!state) {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
        }

        return state;
    }

}
