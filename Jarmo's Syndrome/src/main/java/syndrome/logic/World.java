package syndrome.logic;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import syndrome.logic.conc.SyndromeTimer;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.logic.projectile.Projectile;

/**
 * Represents the game world.
 * Holds information about entities, projectiles, timers etc.
 * @author Axel Wallin
 */
public class World {
    
    private final Player player;
    private final List<NPC> activeNPCs;
    private final List<Projectile> activeProjectiles;
    private AnimationTimer timer;
    private Rectangle rect;
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
     * Adds a new projectile to the game world.
     * 
     * @param proj the projectile to add.
     */
    public void addProjectile(Projectile proj) {
        activeProjectiles.add(proj);
        gamePane.getChildren().add(proj.getObject());
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
    
    public void setGamePane(Pane pane) {
        this.gamePane = pane;
    }
    
    public Pane getGamePane() {
        return gamePane;
    }
    
    /**
     * Sets out current rectangle test to <b>rect</b> and
     * starts the main timer.
     * @param rect the rectangle to be drawn.
     */
    public void setRTest(Rectangle rect) {
        this.rect = rect;
        this.timer = new SyndromeTimer(rect);
        timer.start();
    }
    
    public AnimationTimer getTimer() {
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
    
    public Rectangle getRTest() {
        return rect;
    }

}
