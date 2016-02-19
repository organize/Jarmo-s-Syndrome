/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package syndrome.logic.map;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Axel Wallin
 */
public class DirectionTest {
    
    @Test
    public void testValues() {
        Direction north =  Direction.NORTH;
        assertTrue(north.getPrimary() != Direction.EAST.getPrimary());
        assertTrue(north.getSecondary() == null);
    }

}
