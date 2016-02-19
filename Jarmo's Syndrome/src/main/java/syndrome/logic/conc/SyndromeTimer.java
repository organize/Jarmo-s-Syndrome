package syndrome.logic.conc;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import syndrome.logic.World;
import syndrome.logic.entity.NPC;
import syndrome.logic.entity.Player;
import syndrome.logic.map.Axis;
import syndrome.logic.map.Direction;
import syndrome.logic.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * Represents a periodic timer,
 * <b>this.handle</b> is called 60 times per second
 * after a call to start this timer has been issued.
 * 
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
     * 
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
            
            rect.setTranslateX(player.getLocation().getX());
            rect.setTranslateY(player.getLocation().getY());
            
            rect.setRotate(player.getRotation());
            
            if(player.getDirection() != Direction.NONE) {
                handleScreenPan(player);
            }
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
    
    private void handleScreenPan(Player player) {
        List<Projectile> projectiles = SyndromeFactory.getWorld().getProjectiles();
        Node background = SyndromeFactory.getGUIManager().getBackground();
        Axis toUpdate = Axis.NONE;
        
        double absX = Math.abs(player.getLocation().getX());
        double absY = Math.abs(player.getLocation().getY());
        
        if(absX <= 180 && absY > 140) {
            toUpdate = Axis.X_AXIS;
        }
        if(absY <= 140 && absX > 180) {
            toUpdate = Axis.Y_AXIS;
        }
        if(absX <= 180 && absY <= 140) {
            toUpdate = Axis.X_AND_Y_AXIS;
        }
        
        switch(toUpdate) {
            case X_AXIS:
                background.setTranslateX(-player.getLocation().getX());
                break;
            case Y_AXIS:
                background.setTranslateY(-player.getLocation().getY());
                break;
            case X_AND_Y_AXIS:
                background.setTranslateX(-player.getLocation().getX());
                background.setTranslateY(-player.getLocation().getY());
                break;
        }
        final Axis lambdaAxis = toUpdate;
        if(lambdaAxis != Axis.NONE) {
            projectiles.forEach(proj -> proj.updateTranslation(lambdaAxis));
        }
    }
}
