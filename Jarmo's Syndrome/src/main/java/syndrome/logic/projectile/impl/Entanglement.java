package syndrome.logic.projectile.impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import syndrome.logic.map.Axis;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.logic.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * A simple projectile implementation that is currently used for testing.
 * 
 * @todo {remove test code, finish implementation}
 * @author Axel Wallin
 */
public class Entanglement implements Projectile {
    
    private final Timeline timeline;
    private final KeyFrame keyFrame;
    private final Shape object;
    private final Location destination;
    private double deltaX, deltaY;
    
    public Entanglement(Location where) {
        this.timeline = new Timeline();
        this.keyFrame = constructKeyFrame();
        this.deltaX = 0;
        this.deltaY = 0;
        this.object = new Circle(5);
        this.destination = where;
        setPosition();
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
        SyndromeFactory.getWorld().removeProjectile(this);
    }
    
    @Override
    public Shape getObject() {
        return object;
    }

    private KeyFrame constructKeyFrame() {
        return new KeyFrame(Duration.seconds(0.010), (ActionEvent event) -> {
            /*Circle obj = (Circle) object;
            Random random = new Random();
            double delta = random.nextInt(3);*/
            /*if(destination.getX() != obj.getLayoutX()
                    && destination.getY() != obj.getLayoutY()) {
                if(destination.getX() > obj.getLayoutX()) {
                    deltaX = delta;
                }
                if(destination.getX() < obj.getLayoutX()) {
                    deltaX = -delta;
                }
                if(destination.getY() > obj.getLayoutY()) {
                    deltaY = delta;
                }
                if(destination.getY() < obj.getLayoutY()) {
                    deltaY = -delta;
                }
                obj.setLayoutX(obj.getLayoutY() + deltaX);
                obj.setLayoutY(obj.getLayoutY() + deltaY);
            } else {
                this.destroy();
            }*/
            timeline.setOnFinished((ActionEvent subHandler) -> {
                this.destroy();
            });
        });
    }

    public Timeline getTimeline() {
        return timeline;
    }
    
    private void setPosition() {
        Circle obj = (Circle) object;
        obj.setTranslateX(destination.getX());
        obj.setTranslateY(destination.getY());
    }

    @Override
    public void updateTranslation(Axis axis) {
        Circle obj = (Circle) object;
        Direction direction = SyndromeFactory.getWorld().getPlayer().getDirection();
        int[] deltas = SyndromeFactory.getToolbox().directionToDelta(direction);
        obj.setTranslateX(obj.getTranslateX() - deltas[0]);
        obj.setTranslateY(obj.getTranslateY() - deltas[1]);
    }
}
