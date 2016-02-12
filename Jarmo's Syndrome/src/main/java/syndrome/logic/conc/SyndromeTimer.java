package syndrome.logic.conc;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import syndrome.logic.World;
import syndrome.logic.entity.NPC;
import syndrome.logic.entity.Player;
import syndrome.other.SyndromeFactory;

public class SyndromeTimer extends AnimationTimer {
    
    private boolean paused;
    private Rectangle rect;
    private long lastUpdate;
    
    public SyndromeTimer(Rectangle rect) {
        this.rect = rect;
        this.lastUpdate = 0;
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
