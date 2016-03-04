package syndrome;

import java.awt.Dimension;
import static org.junit.Assert.*;

import org.junit.Test;
import syndrome.logic.map.Location;

public class GameSettingsTest {
    
    @Test
    public void testDimensions() {
        GameSettings settings = new GameSettings();
        Dimension other = new Dimension(640, 480);
        assertTrue(settings.getResolution().equals(other));
        other.setSize(30, 30);
        assertFalse(settings.getResolution().equals(other));
        settings.setResolution(30, 30);
        assertTrue(settings.getResolution().equals(other));
    }

}
