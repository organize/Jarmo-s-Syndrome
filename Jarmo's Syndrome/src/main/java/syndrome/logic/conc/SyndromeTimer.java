package syndrome.logic.conc;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import syndrome.logic.World;
import syndrome.logic.entity.NPC;
import syndrome.logic.entity.Player;
import syndrome.other.SyndromeFactory;

/**
 * Represents a timer.
 * <b>this.handle</b> is called 60 times per second
 * after a call to start this timer has been issued.
 * @author Axel Wallin
 */
public class SyndromeTimer extends AnimationTimer {
    
    private boolean paused;
    private Rectangle rect;
    private long lastUpdate;
    
    public SyndromeTimer(Rectangle rect) {
        this.rect = rect;
        this.lastUpdate = 0;
    }

    /**
     * Handles an <b>AnimationTimer</b> tick.
     * @note this method should never be called manually.
     * @param now, the current time in millis.
     */
    @Override
    public void handle(long now) {
        if(!paused) {
            World world = SyndromeFactory.getWorld();
            
            Player player = world.getPlayer();
            player.tick();
            
            List<NPC> activeNPCs = world.getNPCs();
            activeNPCs.forEach(npc -> npc.tick());

            rect.setX(player.getLocation().getX());
            rect.setY(player.getLocation().getY());

            rect.setRotate(player.getRotation());
        }
        this.lastUpdate = now;
    }
    
    public void setPaused(boolean bool) {
        this.paused = bool;
    }
    
    public boolean isPaused() {
        return paused;
    }

    public Rectangle getRectangle() {
        return rect;
    }
}
