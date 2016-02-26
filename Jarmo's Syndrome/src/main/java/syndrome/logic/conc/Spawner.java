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
 * A class to handle the spawning of NPCs.
 * 
 * @author Axel Wallin
 */
public class Spawner {
    
    private int entityLimit;
    private long lastUpdate;
    private boolean waveFinished;
    
    private Random random;
    
    /**
     * Creates a new spawner instance with a default 
     * concurrent entity limit of 5.
     */
    public Spawner() {
        this.entityLimit = 5;
        this.waveFinished = false;
        this.lastUpdate = 0;
        this.random = new Random();
    }
 
    /**
     * Handles the main logic related to this class.
     * This method will spawn NPCs if the world contains
     * no NPCs, apart from bacteria as they assist the player.
     * This method is called from the main loop.
     * 
     * @param player the player of the game.
     * @param now the current time in nanos.
     */
    public void tick(Player player, long now) {
        int actualSize = SyndromeFactory.getWorld().getNPCs().size()
                - SyndromeFactory.getToolbox().countActive(Bacteria.class);
        if(!waveFinished) {
            if(actualSize >= entityLimit) {
                waveFinished = true;
            }
            if(now - lastUpdate > random.nextInt(300000000) + 50000000) {
                spawnNPC();
                lastUpdate = now;
            }
        } else {
            if(actualSize == 0) {
                waveFinished = false;
                player.refresh();
            }
        }
        updateLimit(player.getLevel());
    }
    
    private void updateLimit(int playerLevel) {
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
        int bacteriaCount = SyndromeFactory.getToolbox().countActive(Bacteria.class);
        if(random.nextInt(bacteriaChance + (20 / playerLevel) + 1) == 1
                && bacteriaCount < playerLevel) {
            NPC bacteria = new Bacteria(Location.createRandomLocation());
            bacteria.render();
            SyndromeFactory.getWorld().addNPC(bacteria);
        }
    }
    
    private void checkKupfferSpawns(int playerLevel) {
        int kupfferCount = SyndromeFactory.getToolbox().countActive(KupfferCell.class);
        if(random.nextInt(5) == 1 && kupfferCount < 5 + (playerLevel * 2)) {
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
        if(random.nextInt(5) == 1) {
            NPC endo = new EndothelialCell(Location.createRandomLocation());
            endo.render();
            SyndromeFactory.getWorld().addNPC(endo);
        }
    }
}
