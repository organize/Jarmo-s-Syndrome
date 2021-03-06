package syndrome.logic.conc;

import java.awt.Robot;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import syndrome.entity.Player;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;

public class SyndromeTimerTest {
    
    private SyndromeTimer timer;
    
    @Before
    public void setUp() {
        this.timer = new SyndromeTimer();
        SyndromeFactory.getWorld().setPlayerModel(new Rectangle(30, 30));
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
        player.setRotation(315.0);
        timer.handle(0);
        timer.stop();
        
        Rectangle model = SyndromeFactory.getWorld().getPlayerModel();
        Location rectLoc = new Location(model.getTranslateX(),
            model.getTranslateY());
        assertTrue(rectLoc.equals(player.getLocation()));
        System.out.println(model.getRotate());
        assertTrue(model.getRotate() == 315.0d);
        assertFalse(player.getLocation().equals(initial));
        timer.setPaused(true);
        timer.start();
        player.setLocation(130, 105);
        timer.handle(10);
        timer.stop();
        assertFalse(rectLoc.equals(player.getLocation()));
    }

}
