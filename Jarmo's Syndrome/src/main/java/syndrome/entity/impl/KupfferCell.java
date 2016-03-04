package syndrome.entity.impl;

import com.sun.javafx.scene.traversal.Direction;
import java.util.Collections;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import syndrome.entity.Entity;
import syndrome.entity.NPC;
import syndrome.entity.objective.Objective;
import syndrome.logic.World;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.projectile.impl.HealCell;
import syndrome.other.SyndromeFactory;
import syndrome.annotation.ObjectiveInfo;

/**
 * Represents a Kupffer cell.
 * 
 * The function is to kill off any bacteria in the liver.
 * Destroying Kupffer cells helps bacteria help the player.
 * 
 * @author Axel Wallin
 */
@ObjectiveInfo(objectives = {Objective.ATTACK_BACTERIA, Objective.ATTACK_PLAYER})
public class KupfferCell extends NPC {
    
    /**
     * The body element of this NPC.
     */
    private final Circle body;
       
    /**
     * The label on top of the body of this NPC.
     */
    private final Text label;
    
    /**
     * The target (enemy/friend) of this NPC.
     */
    private Entity target;
    
    /**
     * The direction of the animation of this NPC (UP/DOWN).
     */
    private Direction animationDirection;
    
    /**
     * Creates a new kupffer cell instance to the specified location.
     * 
     * @param location the location where to spawn this instance.
     */
    public KupfferCell(Location location) {
        super(location);
        super.speed = 0.3;
        super.health = 500;
        
        this.body = new Circle();
        this.label = new Text();
        this.animationDirection = Direction.DOWN;
    }
    
    @Override
    public void tick(long now) {
        super.tick(now);
        
        body.setTranslateX(location.getX());
        body.setTranslateY(location.getY());
        
        label.setTranslateX(location.getX());
        label.setTranslateY(location.getY());
        
        attack();
        animate();
    }

    @Override
    public void render() {
        body.setFill(Paint.valueOf(Color.BLUEVIOLET.toString()));
        body.setRadius(30.0F);
        label.setText("K");
        label.setFont(Font.font("8BIT WONDER", 14));
        label.setFill(Paint.valueOf("white"));
        SyndromeFactory.getWorld()
                .getGamePane().getChildren().addAll(body, label);
    }

    @Override
    public void handleCollision(Projectile projectile) {
        if(!(projectile instanceof HealCell)) {
            super.health -= 25;
            if(super.health <= 0) {
                SyndromeFactory.getWorld().getPlayer().addPoints(100);
            }
        }
    }
    
    @Override
    public int getSize() {
        return (int) body.getRadius();
    }
    
    @Override
    public void destroy() {
        super.destroy();
        World world = SyndromeFactory.getWorld();
        world.getGamePane().getChildren().removeAll(body, label);
    }
    
    private void attack() {
        List<NPC> worldNPCs = SyndromeFactory.getWorld().getNPCs();
        if(target == null) {
            target = findTarget();
        }
        if(target instanceof NPC && !worldNPCs.contains((NPC) target)) {
            target = findTarget();
        }
        super.moveToward(target);
        if(target.getLocation().distanceTo(location) <= 3) {
            target.destroy();
        }
    }
    
    private void animate() {
        double currentRadius = body.getRadius();
        if(currentRadius >= 30) {
            animationDirection = Direction.DOWN;
        }
        if(currentRadius <= 20) {
            animationDirection = Direction.UP;
        }
        if(animationDirection == Direction.DOWN) {
            body.setRadius(currentRadius - 0.10);
        }
        if(animationDirection == Direction.UP) {
            body.setRadius(currentRadius + 0.10);
        }
    }
    
    private Entity findTarget() {
        List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
        Collections.shuffle(activeNPCs);
        for(NPC npc : activeNPCs) {
            if((npc instanceof Bacteria)) {
                return npc;
            }
        }
        return SyndromeFactory.getWorld().getPlayer();
    }

}
