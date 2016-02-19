package syndrome.logic;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import syndrome.entity.Entity;
import syndrome.entity.NPC;
import syndrome.logic.map.Location;
import syndrome.logic.projectile.Projectile;
import syndrome.logic.projectile.impl.Entanglement;

public class WorldTest {
    
    private World world;
    
    @Before
    public void setUp() {
        this.world = new World();
    }
    
    @Test
    public void worldTest() {
        assertTrue(world.getPlayer() != null);
        assertTrue(world.getNPCs() != null);
        assertTrue(world.getProjectiles() != null);
        world.setRTest(new Rectangle(30, 30));
        assertTrue(world.getTimer() != null);
    }
    
    @Test
    public void testProjectiles() {
        Projectile test = new Entanglement(new Location(0, 0));
        world.addProjectile(test);
        assertTrue(world.getProjectiles().contains(test));
        assertTrue(world.getProjectiles().size() == 1);
        world.removeProjectile(test);
        assertFalse(world.getProjectiles().contains(test));
        assertTrue(world.getProjectiles().isEmpty());
    }
    
    @Test
    public void testNPCs() {
        NPC test = new NPC();
        world.addNPC(test);
        assertTrue(world.getNPCs().contains(test));
        assertTrue(world.getNPCs().size() == 1);
        world.removeNPC(test);
        assertFalse(world.getNPCs().contains(test));
        assertTrue(world.getNPCs().isEmpty());
    }
    
    @Test
    public void testPane() {
        Pane pane = new Pane();
        world.setGamePane(pane);
        assertTrue(world.getGamePane().equals(pane));
    }

}
