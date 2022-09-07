package de.knacrack.enhanced_survival.items.list;

import de.knacrack.enhanced_survival.Main;
import de.knacrack.enhanced_survival.items.ICustomItem;
import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import de.knacrack.enhanced_survival.utils.ItemAPI;
import de.knacrack.enhanced_survival.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TimberAxe extends ListenerConstructor implements ICustomItem {

    private static ICustomItem item;

    public TimberAxe() {
        super("TimberingAxe");
        if(item == null) {
            item = this;
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemAPI(Material.IRON_AXE).setName(getItemName()).setCustomModelData(getId()).get();
    }

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public String getItemName() {
        return "Holzfälleraxt";
    }

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), "timbering_axe");
    }

    @EventHandler
    public void treeChopping(BlockBreakEvent event) {
        if (event.getPlayer().getActiveItem().isSimilar(getItem()) && Utils.isLog(event.getBlock().getType())) {

        }
    }

    private void chopping(Location loc) {
        for(int i = -1; i<3; i++){
            for (int j = -1; j<3; j++) {
                for (int v = -1; v<3; v++){

                }
            }
        }
    }
}
