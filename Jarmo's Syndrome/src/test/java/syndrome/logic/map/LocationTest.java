package syndrome.logic.map;

import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {
    
    @Test
    public void testDistances() {
        Location first = new Location();
        Location second = new Location(50, 50);
        assertEquals(70.7, first.distanceTo(second), 0.02);
    }
    
    @Test
    public void testDistances2() {
        Location first = new Location();
        Location second = new Location();
        assertEquals(0, first.distanceTo(second), 0);
    }
    
    @Test
    public void testDistances3() {
        Location first = new Location(-100, -9);
        Location second = new Location(-100, 9);
        assertEquals(9 * 2, first.distanceTo(second), 0);
    }
    
    @Test
    public void testMidpoint() {
        Location first = new Location();
        Location second = new Location(5, 5);
        Location expected = new Location(2.5, 2.5);
        assertEquals("midpoint failure", expected, first.midpoint(second));
    }
    
    @Test
    public void testMidpoint2() {
        Location first = new Location(-5, 5);
        Location second = new Location(10, 80);
        Location expected = new Location(2.5, 42.5);
        assertEquals("midpoint failure", expected, first.midpoint(second));
    }
    
    @Test
    public void testHashCode() {
        Location toTest = new Location(10, 10);
        assertTrue(toTest.hashCode() == -1935091561);
    }

}
