package syndrome.logic.conc;

import java.util.Random;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.entity.impl.Antibody;
import syndrome.entity.impl.Bacteria;
import syndrome.entity.impl.EndothelialCell;
import syndrome.entity.impl.KupfferCell;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;

/**
 *
 * @author Axel Wallin
 */
public class Spawner {
    
    private int entityLimit;
    private long lastUpdate;
    private boolean waveFinished;
    
    public Spawner() {
        this.entityLimit = 5;
        this.waveFinished = false;
        this.lastUpdate = 0;
    }
 
    public void tick(Player player, long now) {
        int actualSize = SyndromeFactory.getWorld().getNPCs().size()
                - SyndromeFactory.getToolbox().countActive(Bacteria.class);
        if(!waveFinished) {
            if(actualSize >= entityLimit) {
                waveFinished = true;
            }
            if(now - lastUpdate > new Random().nextInt(300000000) + 100000000) {
                spawnNPC();
                lastUpdate = now;
            }
        } else {
            if(actualSize == 0) {
                waveFinished = false;
            }
        }
        updateLimit(player.getLevel());
    }
    
    public void updateLimit(int playerLevel) {
        this.entityLimit = (10 * playerLevel) / 5;
    }
    
    private void spawnNPC() {
        int playerLevel = SyndromeFactory.getWorld().getPlayer().getLevel();
        checkLuckySpawns(playerLevel);
        checkKupfferSpawns(playerLevel);
        checkAntibodySpawns(playerLevel);
        checkEndoSpawns(playerLevel);
    }
    
    private void checkLuckySpawns(int playerLevel) {
        int bacteriaChance = SyndromeFactory.getToolbox().countActive(Antibody.class);
        if(new Random().nextInt(bacteriaChance + (10 / playerLevel) + 1) == 1) {
            NPC bacteria = new Bacteria(Location.createRandomLocation());
            bacteria.render();
            SyndromeFactory.getWorld().addNPC(bacteria);
        }
    }
    
    private void checkKupfferSpawns(int playerLevel) {
        int kupfferCount = SyndromeFactory.getToolbox().countActive(KupfferCell.class);
        if(new Random().nextInt(5) == 1 && kupfferCount < 5 + (playerLevel * 2)) {
            NPC kupffer = new KupfferCell(Location.createRandomLocation());
            kupffer.render();
            SyndromeFactory.getWorld().addNPC(kupffer);
        }
    }
    
    private void checkAntibodySpawns(int playerLevel) {
        NPC antibody = new Antibody(Location.createRandomLocation());
        antibody.render();
        SyndromeFactory.getWorld().addNPC(antibody);
    }

    private void checkEndoSpawns(int playerLevel) {
        NPC endo = new EndothelialCell(Location.createRandomLocation());
        endo.render();
        SyndromeFactory.getWorld().addNPC(endo);
    }
}
