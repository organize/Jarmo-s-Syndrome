package syndrome.logic.projectile.impl;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import static org.junit.Assert.*;
import org.junit.Test;
import syndrome.logic.map.Axis;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;

public class EntanglementTest {
    
    @Test
    public void testTimelineAttributes() {
        Pane pane = new Pane();
        SyndromeFactory.getWorld().setGamePane(pane);
        Entanglement test = new Entanglement(new Location(0, 0), 90);
        Timeline timeline = test.getTimeline();
        test.fire();
        assertTrue(timeline.getCycleCount() == 250);
        assertFalse(timeline.getKeyFrames().isEmpty());
        assertTrue(timeline.getStatus().equals(Status.RUNNING));
        test.destroy();
        assertTrue(timeline.getStatus().equals(Status.STOPPED));
        assertFalse(SyndromeFactory.getWorld().getProjectiles().contains(test));
        assertFalse(pane.getChildren().contains(test.getObject()));
    }
    
    @Test
    public void testPause() {
        Entanglement test = new Entanglement(new Location(0, 0), 90);
        test.togglePause(true);
        assertEquals(test.getTimeline().getStatus(), Status.STOPPED);
    }
    
    @Test
    public void testShape() {
        Entanglement test = new Entanglement(new Location(0, 0), 90);
        Shape actual = test.getObject();
        assertTrue(actual instanceof Circle);
    }
    
    @Test
    public void testInitial() {
        Location location = new Location(10, 10);
        Entanglement entanglement = new Entanglement(location, 90);
        assertTrue(130 == entanglement.getObject().getTranslateX());
        assertTrue(105 == entanglement.getObject().getTranslateY());
    }
    
    @Test
    public void testCoordinateTransform() {
        Location location = new Location(10, 10);
        Entanglement entanglement = new Entanglement(location, 90);
        SyndromeFactory.getWorld().getPlayer().setDirection(Direction.NORTH);
        entanglement.updateTranslation(Axis.X_AXIS);
        System.out.println(entanglement.getObject().getTranslateX());
        assertTrue(location.getX() + 120 == entanglement.getObject().getTranslateX());
    }

}
