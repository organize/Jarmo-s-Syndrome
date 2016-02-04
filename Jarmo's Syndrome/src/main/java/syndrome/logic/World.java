package syndrome.logic;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import syndrome.logic.conc.SyndromeTimer;
import syndrome.logic.entity.NPC;
import syndrome.logic.entity.Player;
import syndrome.logic.projectile.Projectile;

public class World {
    
    private final Player player;
    private final List<NPC> activeNPCs;
    private final List<Projectile> activeProjectiles;
    private AnimationTimer timer;
    private Rectangle rect;
    private Pane gamePane;
    
    public World() {
        this.player = new Player();
        this.activeNPCs = new ArrayList<>();
        this.activeProjectiles = new ArrayList<>();
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
    
    public void addProjectile(Projectile proj) {
        activeProjectiles.add(proj);
    }
    
    public void remvoveProjectile(Projectile proj) {
        activeProjectiles.remove(proj);
    }
    
    public void addNPC(NPC npc) {
        activeNPCs.add(npc);
    }
    
    public void remvoveNPC(NPC npc) {
        activeNPCs.remove(npc);
    }
    
    public void setGamePane(Pane pane) {
        this.gamePane = pane;
    }
    
    public Pane getGamePane() {
        return gamePane;
    }
    
    /* tempcode */
    public void setRTest(Rectangle rect) {
        this.rect = rect;
        this.timer = new SyndromeTimer(rect);
        timer.start();
    }

}
