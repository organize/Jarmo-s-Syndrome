package syndrome.logic.entity;

import static org.junit.Assert.*;

import org.junit.Test;
import syndrome.logic.map.Location;

public class EntityTest {
    
    @Test
    public void testTypes() {
        Player player = new Player();
        assertTrue(player.getType().equals(EntityType.PLAYER));
        NPC npc = new NPC();
        assertTrue(npc.getType().equals(EntityType.NPC));
    }
    
    @Test
    public void testSetPosition() {
        Entity player = new Player();
        Entity npc = new NPC();
        player.setLocation(20, -10);
        npc.setLocation(20.0, -10.01);
        Location location = new Location(20, -10.01);
        assertFalse(player.getLocation().equals(location));
        assertTrue(npc.getLocation().equals(location));
    }

    
    @Test
    public void testCollisionBox() {
        Entity player = new Player();
        /* player position is 300, 300 initially */
        /* so bounds are [300,300][308,300][300,308][308,308] */
        Entity testNpc = new NPC();
        testNpc.setLocation(302, 302);
        assertTrue(player.collidesWith(testNpc));
        testNpc.setLocation(299, 299);
        assertFalse(player.collidesWith(testNpc));
        testNpc.setLocation(300, 300);
        assertTrue(player.collidesWith(testNpc));
        testNpc.setLocation(304, 304);
        assertTrue(player.collidesWith(testNpc));
        
    }
}
