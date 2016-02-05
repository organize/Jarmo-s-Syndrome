package syndrome.logic.projectile.impl;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import static org.junit.Assert.*;
import org.junit.Test;
import syndrome.other.SyndromeFactory;

public class EntanglementTest {
    
    @Test
    public void testTimelineAttributes() {
        SyndromeFactory.getWorld().setGamePane(new Pane());
        Entanglement test = new Entanglement();
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
        Entanglement test = new Entanglement();
        Shape actual = test.getObject();
        assertTrue(actual instanceof Circle);
        Circle circle = (Circle) actual;
        assertTrue(circle.getCenterX() == shape.getCenterX());
        assertTrue(circle.getCenterY() == shape.getCenterY());
    }

}
