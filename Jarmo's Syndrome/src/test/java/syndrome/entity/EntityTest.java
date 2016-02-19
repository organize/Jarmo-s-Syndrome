package syndrome.entity;

import syndrome.entity.Player;
import syndrome.entity.EntityType;
import syndrome.entity.NPC;
import syndrome.entity.Entity;
import static org.junit.Assert.*;

import org.junit.Test;
import syndrome.logic.map.Direction;
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
    public void testEntityRotation() {
       Player player = new Player();
       player.setRotation(30);
       assertTrue(player.getRotation() == 30);
       
       NPC npc = new NPC();
       npc.setRotation(10.0019);
       assertTrue(npc.getRotation() == 10.0019);
       assertFalse(npc.getRotation() == 10);
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
        assertFalse(testNpc.collidesWith(player));
    }
    
    @Test
    public void testBounds() {
        Player player = new Player();
        Location playerLoc = player.getLocation();
        Location[] actual = player.getBounds();
        assertTrue(actual.length == 4);
        assertTrue(actual[0].equals(playerLoc));
        
        Location right = new Location(playerLoc.getX() + 8,
            playerLoc.getY());
        assertTrue(actual[1].equals(right));
        
        Location lowerLeft = new Location(playerLoc.getX(), 
                playerLoc.getY() + 8);
        assertTrue(actual[2].equals(lowerLeft));
        
        Location lowerRight = new Location(playerLoc.getX() + 8, 	
                playerLoc.getY() + 8);
        assertTrue(actual[3].equals(lowerRight));
    }
    
    @Test
    public void testMove() {
        Player player = new Player();
        final Location initial = player.getLocation();
        player.tick();
        assertTrue(initial.equals(player.getLocation()));
        
        Location expected = new Location(initial.getX(), initial.getY() - 2);
        player.setDirection(Direction.NORTH);
        player.tick();
        assertTrue(expected.equals(player.getLocation()));
    }
}
