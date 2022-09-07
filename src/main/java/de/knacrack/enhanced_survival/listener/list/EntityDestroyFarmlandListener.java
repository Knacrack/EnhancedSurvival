package de.knacrack.enhanced_survival.listener.list;

import de.knacrack.enhanced_survival.listener.ListenerConstructor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class EntityDestroyFarmlandListener extends ListenerConstructor {
    public EntityDestroyFarmlandListener() {
        super("Destroy Farmland");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.PHYSICAL && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.FARMLAND) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean register() {
        return true;
    }
}