package syndrome.logic.conc;

import java.awt.Robot;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import syndrome.logic.entity.Player;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;

public class SyndromeTimerTest {
    
    private SyndromeTimer timer;
    
    @Before
    public void setUp() {
        this.timer = new SyndromeTimer(new Rectangle(30, 30));
    }
    
    @Test
    public void testPauseToggle() {
        timer.start();
        assertFalse(timer.isPaused());
        timer.setPaused(true);
        assertTrue(timer.isPaused());
        timer.stop();
    }
    
    @Test
    public void testTick() {
        Player player = SyndromeFactory.getWorld().getPlayer();
        Location initial = new Location(300, 300);
        Location location = new Location(10, 10);
        try {
            Robot robot = new Robot();
            robot.mouseMove(0, 0);
        } catch(Exception e) {}
        timer.start();
        player.setLocation(location.getX(), location.getY());
        timer.handle(0);
        timer.stop();
        
        Location rectLoc = new Location(timer.getRectangle().getX(),
            timer.getRectangle().getY());
        assertTrue(rectLoc.equals(player.getLocation()));
        System.out.println(timer.getRectangle().getRotate());
        assertTrue(timer.getRectangle().getRotate() == 315.0d);
        assertFalse(player.getLocation().equals(initial));
        timer.setPaused(true);
        timer.start();
        player.setLocation(130, 105);
        timer.handle(10);
        timer.stop();
        assertFalse(rectLoc.equals(player.getLocation()));
    }

}
