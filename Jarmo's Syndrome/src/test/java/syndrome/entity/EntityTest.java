package syndrome.entity;

import static org.junit.Assert.*;

import org.junit.Test;
import syndrome.entity.impl.Antibody;
import syndrome.entity.impl.EndothelialCell;
import syndrome.entity.impl.KupfferCell;
import syndrome.logic.map.Axis;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;

public class EntityTest {
    
    @Test
    public void testTypes() {
        Player player = new Player();
        assertTrue(player.getType().equals(EntityType.PLAYER));
        Entity npc = new Antibody(new Location(0, 0));
        assertTrue(npc.getType().equals(EntityType.NPC));
    }
    
    @Test
    public void testSetPosition() {
        Entity player = new Player();
        Entity npc = new KupfferCell(new Location(0, 0));
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
       
       NPC npc = new KupfferCell(new Location(0, 0));
       npc.setRotation(10.0019);
       assertTrue(npc.getRotation() == 10.0019);
       assertFalse(npc.getRotation() == 10);
    }
    
    @Test
    public void testPlayerBounds() {
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
    public void testDeath() {
        NPC test = new Antibody(new Location(0, 0));
        SyndromeFactory.getWorld().addNPC(test);
        test.inflictDamage(30000000);
        test.tick(0);
        assertFalse(SyndromeFactory.getWorld().getNPCs().contains(test));
    }
    
    @Test
    public void testNPCBounds() {
        NPC npc = new KupfferCell(new Location(0, 0));
        Location npcLoc = npc.getLocation();
        Location[] actual = npc.getBounds();
        assertTrue(actual.length == 4);
        assertTrue(actual[0].equals(npcLoc));
        
        Location right = new Location(npcLoc.getX() + 4,
            npcLoc.getY());
        assertTrue(actual[1].equals(right));
        
        Location lowerLeft = new Location(npcLoc.getX(), 
                npcLoc.getY() + 4);
        assertTrue(actual[2].equals(lowerLeft));
        
        Location lowerRight = new Location(npcLoc.getX() + 4, 	
                npcLoc.getY() + 4);
        assertTrue(actual[3].equals(lowerRight));
    }
    
    @Test
    public void testNPCSize() {
        NPC npc = new EndothelialCell(new Location(0, 0));
        /* test that the NPC actually overrides default size */
        assertTrue(npc.getSize() != 4);
    }
    
    @Test
    public void testTranslation() {
        Location pre = new Location(0, 0);
        NPC npc = new EndothelialCell(pre);
        npc.updateTranslation(Axis.X_AXIS);
        assertTrue(npc.getLocation().equals(pre));
    }
    
    @Test
    public void testInflictDamage() {
        NPC npc = new EndothelialCell(new Location(0, 0));
        npc.inflictDamage(30);
        assertTrue(npc.health == 70);
    }
}
