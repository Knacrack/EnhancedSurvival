package de.knacrack.enhanced_survival.utils.runnables;


import de.knacrack.enhanced_survival.modules.playerstats.PlayerStats;

public class PlayerStatsSaveRunnable implements Runnable {


    @Override
    public void run() {
        //TODO: PlayerStats müssen wieder hinzugefügt werden, wenn diese auch wieder abgespeichert werden können!
        // PlayerStats.getAllStats().forEach(PlayerStats::save);
        PlayerStats.getAllStats().forEach(PlayerStats::save);
    }
}
