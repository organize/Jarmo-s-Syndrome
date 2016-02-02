package syndrome.logic.conc;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import syndrome.logic.World;
import syndrome.logic.entity.NPC;
import syndrome.logic.entity.Player;
import syndrome.other.SyndromeFactory;

/**
 *
 * @author Axel Wallin
 */
public class SyndromeTimer extends AnimationTimer {
    
    private boolean paused;
    private Rectangle rect;
    
    public SyndromeTimer(Rectangle rect) {
        this.rect = rect;
    }

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
        }
    }
    
    public void setPaused(boolean bool) {
        this.paused = bool;
    }

}
