package syndrome.logic.projectile.impl;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import syndrome.logic.map.Location;
import syndrome.logic.projectile.Projectile;
import syndrome.other.SyndromeFactory;

public class Entanglement implements Projectile {
    
    private final Timeline timeline;
    private final KeyFrame keyFrame;
    private final Shape object;
    private double deltaX, deltaY;
    
    public Entanglement() {
        this.timeline = new Timeline();
        this.keyFrame = constructKeyFrame();
        this.deltaX = 0;
        this.deltaY = 0;
        this.object = new Circle(15, 15, 5);
    }

    @Override
    public void fire() {
        timeline.setCycleCount(1000);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @Override
    public void destroy() {
        timeline.stop();
        Pane pane = SyndromeFactory.getWorld().getGamePane();
        if(!pane.getChildren().isEmpty()) {
            pane.getChildren().remove(object);
        }
    }
    
    @Override
    public Shape getObject() {
        return object;
    }

    private KeyFrame constructKeyFrame() {
        return new KeyFrame(Duration.seconds(0.010), (ActionEvent event) -> {
            Location destination = SyndromeFactory.
                    getWorld().getPlayer().getLocation();
            Circle obj = (Circle) object;
            Random random = new Random();
            double delta = random.nextInt(3) + 0.2;
            if(destination.getX() != obj.getCenterX()
                    && destination.getY() != obj.getCenterY()) {
                if(destination.getX() > obj.getCenterX()) {
                    deltaX = delta;
                }
                if(destination.getX() < obj.getCenterX()) {
                    deltaX = -delta;
                }
                if(destination.getY() > obj.getCenterY()) {
                    deltaY = delta;
                }
                if(destination.getY() < obj.getCenterY()) {
                    deltaY = -delta;
                }
                obj.setCenterX(obj.getCenterX() + deltaX);
                obj.setCenterY(obj.getCenterY() + deltaY);
            }
            timeline.setOnFinished((ActionEvent subHandler) -> {
                this.destroy();
            });
        });
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
