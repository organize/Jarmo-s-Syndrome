package syndrome.entity.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import syndrome.entity.NPC;
import syndrome.entity.objective.Objective;
import syndrome.logic.World;
import syndrome.logic.map.Location;
import syndrome.logic.projectile.Projectile;
import syndrome.logic.projectile.impl.HealCell;
import syndrome.other.SyndromeFactory;

/**
 * Represents a Kupffer cell.
 * 
 * The function is to kill off any bacteria in the liver.
 * Destroying Kupffer cells helps bacteria help the player.
 * 
 * @author Axel Wallin
 */

public class KupfferCell extends NPC {
    
    private Circle body;
    private Text label;
    private NPC target;
    
    public KupfferCell(Location location) {
        super(location);
        super.speed = 0.3;
        super.health = 300;
        this.body = new Circle();
        this.label = new Text();
    }
    
    @Override
    public void tick(long now) {
        super.tick(now);
        
        body.setTranslateX(location.getX());
        body.setTranslateY(location.getY());
        
        label.setTranslateX(location.getX());
        label.setTranslateY(location.getY());
        
        attack();
        
    }

    @Override
    public void render() {
        body.setFill(Paint.valueOf("black"));
        body.setRadius(30.0F);
        label.setText("K");
        label.setFont(Font.font("8BIT WONDER", 14));
        label.setFill(Paint.valueOf("white"));
        SyndromeFactory.getWorld()
                .getGamePane().getChildren().addAll(body, label);
    }

    @Override
    public List<Objective> getObjective() {
        return new ArrayList<Objective>() {
            {
                add(Objective.ATTACK_BACTERIA);
            }
        };
    }

    @Override
    public void handleCollision(Projectile projectile) {
        if(!(projectile instanceof HealCell)) {
            super.health -= 25;
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
        if(target == null || !SyndromeFactory.getWorld().getNPCs().contains(target)) {
            target = findTarget();
        } else {
            super.moveToward(target);
            if(target.getLocation().distanceTo(location) <= 3) {
                target.destroy();
            }
        }
    }
    
    private NPC findTarget() {
        List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
        Collections.shuffle(activeNPCs);
        for(NPC npc : activeNPCs) {
            if((npc instanceof Bacteria)) {
                return npc;
            }
        }
        return null;
    }

}
