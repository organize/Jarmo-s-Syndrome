package syndrome.logic;

import syndrome.logic.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import syndrome.logic.conc.SyndromeTimer;
import syndrome.logic.entity.NPC;
import syndrome.logic.entity.Player;

/**
 *
 * @author Axel Wallin
 */
public class World {
    
    private Player player;
    private List<NPC> activeNPCs;
    private AnimationTimer timer;
    private Rectangle rect;
    
    public World() {
        this.player = new Player();
        this.activeNPCs = new ArrayList<>();
        
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public List<NPC> getNPCs() {
        return activeNPCs;
    }
    
    public void addNPC(NPC npc) {
        activeNPCs.add(npc);
    }
    
    public void remvoveNPC(NPC npc) {
        activeNPCs.remove(npc);
    }
    
    public void setRTest(Rectangle rect) {
        this.rect = rect;
        this.timer = new SyndromeTimer(rect);
        timer.start();
    }

}
