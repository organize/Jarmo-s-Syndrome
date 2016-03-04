package syndrome.logic.conc;

import java.util.ConcurrentModificationException;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import syndrome.logic.World;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.logic.map.Direction;
import syndrome.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * Represents a periodic timer,
 * <b>this.handle</b> is called 60 times per second
 * after a call to start this timer has been issued.
 * 
 * @author Axel Wallin
 */
public class SyndromeTimer extends AnimationTimer {
    
    /**
     * The last update time in millis.
     */
    private long lastUpdate;
    
    /**
     * Whether or not the game is paused.
     */
    private boolean paused;
    
    /**
     * The Spawner instance of this class.
     */
    private final Spawner spawner;
    
    /**
     * Creates a new timer instance, and a
     * Spawner instance inside it.
     */
    public SyndromeTimer() {
        this.lastUpdate = 0;
        this.spawner = new Spawner();
    }

    /**
     * Handles an <b>AnimationTimer</b> tick.
     * 
     * @note this method should never be called manually.
     * @param now, the current time in millis.
     */
    @Override
    public void handle(long now) {
        if(!paused) {
            World world = SyndromeFactory.getWorld();
            
            Player player = world.getPlayer();
            player.tick(now);
            
            List<NPC> activeNPCs = world.getNPCs();
            try {
                activeNPCs.forEach(npc -> npc.tick(now));
            } catch(ConcurrentModificationException e) {}
            
            Rectangle playerModel = world.getPlayerModel();
            
            playerModel.setTranslateX(player.getLocation().getX());
            playerModel.setTranslateY(player.getLocation().getY());
            
            playerModel.setRotate(player.getRotation());
            
            if(player.getDirection() != Direction.NONE) {
                SyndromeFactory.getGUIManager().getGameScreen().handleScreenPan(player);
            }
            spawner.tick(player, now);
        }
        this.lastUpdate = now;
    }
    
    /**
     * Pauses the game loop and halts all projectiles.
     * @param bool the pause state.
     */
    public void setPaused(boolean bool) {
        this.paused = bool;
        pauseAllProjectiles();
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    private void pauseAllProjectiles() {
        List<Projectile> projectiles = SyndromeFactory
                .getWorld().getProjectiles();
        projectiles.forEach((p) -> p.togglePause(paused));
    }
    
}
