package syndrome.logic.projectile.impl;

import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import syndrome.entity.NPC;
import syndrome.entity.Player;
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
    private double[] vector;
    
    public Entanglement(Location where, int angle) {
        this.timeline = new Timeline();
        this.keyFrame = constructKeyFrame();
        this.object = new Circle(3);
        this.destination = where;
        this.vector = new double[2];
        setPosition(angle);
    }

    @Override
    public void fire() {
        timeline.setCycleCount(250);
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
            
            Circle obj = (Circle) object;
            double playerLevel = SyndromeFactory.getWorld().getPlayer().getLevel();
            double multiplier = (playerLevel / 3);
            if(multiplier < 3) {
                multiplier = 3;
            }
            obj.setTranslateX(obj.getTranslateX() - (vector[0] * multiplier));
            obj.setTranslateY(obj.getTranslateY() - (vector[1] * multiplier));
            Location location = new Location(obj.getTranslateX(), obj.getTranslateY());
            List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
            activeNPCs.stream()
                    .filter((npc) -> (location.distanceTo(npc.getLocation()) < npc.getSize() + 3))
                    .forEach((instance) -> {
                instance.handleCollision(this);
                this.destroy();
            });
            timeline.setOnFinished((ActionEvent subHandler) -> {
                this.destroy();
            });
        });
    }

    public Timeline getTimeline() {
        return timeline;
    }
    
    private void setPosition(double angle) {
        Circle obj = (Circle) object;
        Player player = SyndromeFactory.getWorld().getPlayer();
        obj.setTranslateX(player.getLocation().getX());
        obj.setTranslateY(player.getLocation().getY());
        vector = SyndromeFactory.getToolbox().angleToVelocity((player.getRotation() + angle) * (Math.PI / 180));
    }

    @Override
    public void updateTranslation(Axis axis) {
        Circle obj = (Circle) object;
        Direction direction = SyndromeFactory.getWorld().getPlayer().getDirection();
        double[] deltas = SyndromeFactory.getToolbox().directionToDelta(direction);
        if(axis == Axis.X_AXIS) {
            obj.setTranslateX(obj.getTranslateX() - deltas[0]);
        }
        if(axis == Axis.Y_AXIS) {
            obj.setTranslateY(obj.getTranslateY() - deltas[1]);
        }
        if(axis == Axis.X_AND_Y_AXIS) {
            obj.setTranslateX(obj.getTranslateX() - deltas[0]);
            obj.setTranslateY(obj.getTranslateY() - deltas[1]);
        }
            
    }

    @Override
    public void togglePause(boolean state) {
        if(state) {
            timeline.pause();
        } else {
            timeline.play();
        }
    }
}
