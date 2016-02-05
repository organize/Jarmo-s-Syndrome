package syndrome;

import java.awt.Dimension;
import static org.junit.Assert.*;

import org.junit.Test;
import syndrome.logic.map.Location;

public class GameSettingsTest {
    
    @Test
    public void testPause() {
        GameSettings settings = new GameSettings();
        settings.togglePause();
        assertTrue(settings.isPaused());
    }
    
    @Test
    public void testDimensions() {
        GameSettings settings = new GameSettings();
        Dimension other = new Dimension(1024, 768);
        assertTrue(settings.getResolution().equals(other));
        other.setSize(30, 30);
        assertFalse(settings.getResolution().equals(other));
        settings.setResolution(30, 30);
        assertTrue(settings.getResolution().equals(other));
    }

}
