package syndrome.logic.projectile.impl;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import syndrome.entity.NPC;
import syndrome.entity.impl.EndothelialCell;
import syndrome.logic.map.Axis;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * A projectile implementation that is fired by enemies,
 * designed to heal antibodies.
 * 
 * @author Axel Wallin
 */
public class HealCell implements Projectile {
    
    private final Timeline timeline;
    private final KeyFrame keyFrame;
    private final Shape shape;
    private final NPC target;
    private final Location source;
    
    /**
     * Creates a new instance to the specified location,
     * with the specified target NPC.
     * 
     * @param source the initial position of this projectile.
     * @param target the destination of this projectile.
     */
    public HealCell(Location source, NPC target) {
        this.timeline = new Timeline();
        this.shape = new Circle(2);
        this.target = target;
        this.source = source;
        this.keyFrame = constructKeyFrame();
    }

    @Override
    public void fire() {
        timeline.setCycleCount(1000);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        shape.setFill(Paint.valueOf("green"));
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
            /* Check if the target still exists */
            List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
            if(!activeNPCs.contains(target)) {
                this.destroy();
            }
            
            /* Move towards the specified entity */
            moveTowardsTarget();
            
            /* Check for collision */
            Location location = new Location(shape.getTranslateX(), shape.getTranslateY());
            activeNPCs.stream()
                    .filter((npc) -> (location.distanceTo(npc.getLocation()) < npc.getSize()))
                    .forEach((instance) -> {
                if(!(instance instanceof EndothelialCell)) {
                    instance.handleCollision(this);
                    this.destroy();
                }
            });
            
            /* Destroy hook in the end */
            timeline.setOnFinished((ActionEvent) -> {
                this.destroy();
            });
        });
    }

    public Timeline getTimeline() {
        return timeline;
    }

    private void moveTowardsTarget() {
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
