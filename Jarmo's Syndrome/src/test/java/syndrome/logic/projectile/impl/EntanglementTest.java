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
        SyndromeFactory.getWorld().setGamePane(new Pane());
        Entanglement test = new Entanglement(new Location(0, 0));
        Timeline timeline = test.getTimeline();
        test.fire();
        assertTrue(timeline.getCycleCount() > 0);
        assertFalse(timeline.getKeyFrames().isEmpty());
        assertTrue(timeline.getStatus().equals(Status.RUNNING));
        test.destroy();
        assertTrue(timeline.getStatus().equals(Status.STOPPED));
    }
    
    @Test
    public void testShape() {
        Circle shape = new Circle(15, 15, 5);
        Entanglement test = new Entanglement(new Location(0, 0));
        Shape actual = test.getObject();
        assertTrue(actual instanceof Circle);
        Circle circle = (Circle) actual;
        assertTrue(circle.getTranslateX() == shape.getTranslateX());
        assertTrue(circle.getTranslateY() == shape.getTranslateY());
    }
    
    @Test
    public void testInitial() {
        Location location = new Location(10, 10);
        Entanglement entanglement = new Entanglement(location);
        assertTrue(location.getX() == entanglement.getObject().getTranslateX());
        assertTrue(location.getY() == entanglement.getObject().getTranslateY());
    }
    
    @Test
    public void testCoordinateTransform() {
        Location location = new Location(10, 10);
        Entanglement entanglement = new Entanglement(location);
        SyndromeFactory.getWorld().getPlayer().setDirection(Direction.NORTH);
        entanglement.updateTranslation(Axis.X_AXIS);
        System.out.println(entanglement.getObject().getTranslateX());
        assertTrue(location.getX() + 2 == entanglement.getObject().getTranslateX() + 2);
    }

}
