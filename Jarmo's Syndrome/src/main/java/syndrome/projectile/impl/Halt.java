package syndrome.projectile.impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import syndrome.entity.Player;
import syndrome.logic.map.Axis;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * A projectile implementation that is fired by enemies,
 * designed to slow down the player.
 * 
 * @author Axel Wallin
 */
public class Halt implements Projectile {
    
    /**
     * The timeline instance of this projectile.
     */
    private final Timeline timeline;
    
    /**
     * The keyframe instance of this projectile.
     */
    private final KeyFrame keyFrame;
    
    /**
     * The object (item to animate) of this projectile.
     */
    private final Shape shape;
    
    /**
     * The initial position of this projectile.
     */
    private final Location source;
    
    /**
     * Creates a new halt instance of the specified location.
     * 
     * @param source the initial position of this projectile.
     */
    public Halt(Location source) {
        this.timeline = new Timeline();
        this.shape = new Rectangle(3, 3);
        this.source = source;
        this.keyFrame = constructKeyFrame();
    }

    @Override
    public void fire() {
        timeline.setCycleCount(300);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        shape.setFill(Paint.valueOf("red"));
    }

    @Override
    public void destroy() {
        timeline.stop();
        Pane pane = SyndromeFactory.getWorld().getGamePane();
        if(!pane.getChildren().isEmpty()) {
            pane.getChildren().remove(shape);
        }
        SyndromeFactory.getWorld().removeProjectile(this);
    }
           
    @Override
    public void togglePause(boolean state) {
        if(state) {
            timeline.pause();
        } else {
            timeline.play();
        }
    }
    
    @Override
    public Shape getObject() {
        return shape;
    }
    
    @Override
    public void updateTranslation(Axis axis) {
        Direction direction = SyndromeFactory.getWorld().getPlayer().getDirection();
        double[] deltas = SyndromeFactory.getToolbox().directionToDelta(direction);
        if(axis == Axis.X_AXIS) {
            shape.setTranslateX(shape.getTranslateX() - deltas[0]);
        }
        if(axis == Axis.Y_AXIS) {
            shape.setTranslateY(shape.getTranslateY() - deltas[1]);
        }
        if(axis == Axis.X_AND_Y_AXIS) {
            shape.setTranslateX(shape.getTranslateX() - deltas[0]);
            shape.setTranslateY(shape.getTranslateY() - deltas[1]);
        }     
    }

    private KeyFrame constructKeyFrame() {
        shape.setTranslateX(source.getX());
        shape.setTranslateY(source.getY());
        return new KeyFrame(Duration.seconds(0.010), (ActionEvent event) -> {
            Player player = SyndromeFactory.getWorld().getPlayer();
            /* Move towards the specified entity */
            moveTowardsTarget(player);
            
            /* Check for collision */
            Location location = new Location(shape.getTranslateX(), shape.getTranslateY());
            if(player.getLocation().distanceTo(location) < 10) {
                player.handleCollision(this);
                this.destroy();
            }
            
            shape.setRotate(shape.getRotate() - 2);
            /* Destroy hook in the end */
            timeline.setOnFinished((ActionEvent subHandler) -> {
                this.destroy();
            });
        });
    }

    public Timeline getTimeline() {
        return timeline;
    }

    private void moveTowardsTarget(Player target) {
        int deltaX = 0, deltaY = 0;
        Location destination = target.getLocation();
        if(destination.getX() < shape.getTranslateX()) {
            deltaX = -1;
        }
        if(destination.getX() > shape.getTranslateX()) {
            deltaX = 1;
        }
        if(destination.getY() < shape.getTranslateY()) {
            deltaY = -1;
        }
        if(destination.getY() > shape.getTranslateY()) {
            deltaY = 1;
        }
        shape.setTranslateX(shape.getTranslateX() + deltaX);
        shape.setTranslateY(shape.getTranslateY() + deltaY);
    }
}
