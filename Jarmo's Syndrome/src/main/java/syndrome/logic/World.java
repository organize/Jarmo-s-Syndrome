package syndrome.logic;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import syndrome.logic.conc.SyndromeTimer;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.projectile.Projectile;

/**
 * Represents the game world.
 * Holds information about entities, projectiles, timers etc.
 * 
 * @author Axel Wallin
 */
public class World {
    
    /**
     * The player instance.
     */
    private final Player player;
    
    /**
     * List of active NPCs in the game world.
     */
    private final List<NPC> activeNPCs;
    
    /**
     * List of active projectiles in the game world.
     */
    private final List<Projectile> activeProjectiles;
    
    /**
     * The timer instance, which handles the updating of everything.
     */
    private SyndromeTimer timer;
    
    /**
     * The player model instance.
     */
    private Rectangle playerModel;
    
    /**
     * The main game pane, used to draw elements on top of it.
     */
    private Pane gamePane;
    
    /**
     * Creates a new World instance and a Player inside it.
     */
    public World() {
        this.player = new Player();
        this.activeNPCs = new ArrayList<>();
        this.activeProjectiles = new ArrayList<>();
    }
    
    /**
     * Adds a new projectile to the game world
     * and the main game screen.
     * 
     * @param proj the projectile to add.
     * @param addToPane whether or not this projectile is also drawn immediately.
     */
    public void addProjectile(Projectile proj, boolean addToPane) {
        activeProjectiles.add(proj);
        if(addToPane) {
            gamePane.getChildren().add(proj.getObject());
        }
    }
    
    /**
     * Removes a new projectile from the game world.
     * 
     * @param proj the projectile to remove.
     */
    public void removeProjectile(Projectile proj) {
        activeProjectiles.remove(proj);
    }
    
    /**
     * Adds a new NPC to the game world.
     * 
     * @param npc the NPC to add.
     */
    public void addNPC(NPC npc) {
        activeNPCs.add(npc);
    }
    
    /**
     * Removes a new NPC from the game world.
     * 
     * @param npc the NPC to remove.
     */
    public void removeNPC(NPC npc) {
        activeNPCs.remove(npc);
    }

    public void setPlayerModel(Rectangle model) {
        this.playerModel = model;
    }
    
    /**
     * Creates and starts the main game loop.
     */
    public void startTimer() {
        if(playerModel == null) {
            throw new IllegalStateException("player model is null");
        }
        this.timer = new SyndromeTimer();
        timer.start();
    }
    
    public SyndromeTimer getTimer() {
        return timer;
    }
      
    public Player getPlayer() {
        return player;
    }
    
    public List<NPC> getNPCs() {
        return activeNPCs;
    }
    
    public List<Projectile> getProjectiles() {
        return activeProjectiles;
    }
    
    public Rectangle getPlayerModel() {
        return playerModel;
    }
   
    public void setGamePane(Pane pane) {
        this.gamePane = pane;
    }
    
    public Pane getGamePane() {
        return gamePane;
    }
}
