package syndrome.logic.projectile.impl;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
        
        /* tempcode */
        int randX = new Random().nextInt(1000);
        int randY = new Random().nextInt(700);
        this.object = new Circle(randX, randY, new Random().nextInt(10) + 1);
        
        /* tempcode */
        if(new Random().nextInt(2) == 1) {
            this.object.setFill(Paint.valueOf("yellow"));
        } else {
            this.object.setFill(Paint.valueOf("white"));
        }
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
        SyndromeFactory.getWorld()
            .getGamePane().getChildren().remove(object);
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
            
            timeline.setOnFinished((ActionEvent subHandler) -> {
                this.destroy();
            });
        });
    }

}
