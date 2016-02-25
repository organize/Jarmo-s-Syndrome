package syndrome.entity.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import syndrome.entity.NPC;
import syndrome.entity.objective.Objective;
import syndrome.logic.World;
import syndrome.logic.map.Location;
import syndrome.logic.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * Represents a singular bacteria.
 * 
 * The function is to help the player by
 * infiltrating the liver.
 * 
 * @author Axel Wallin
 */

public class Bacteria extends NPC {
    
    private Rectangle body;
    private Text label;
    private NPC target;
    
    public Bacteria(Location location) {
        super(location);
        this.body = new Rectangle();
        this.label = new Text();
    }
    
    @Override
    public void tick(long now) {
        body.setTranslateX(location.getX());
        body.setTranslateY(location.getY());
        
        label.setTranslateX(location.getX());
        label.setTranslateY(location.getY());
        
        body.setRotate(body.getRotate() - 2f);
        
        attack();
        
        updateSpeed();
    }

    @Override
    public void render() {
        body.setWidth(20);
        body.setHeight(20);
        body.setFill(Paint.valueOf(Color.DARKOLIVEGREEN.toString()));
        label.setText("B");
        label.setFont(Font.font("8BIT WONDER", 8));
        label.setFill(Paint.valueOf("white"));
        SyndromeFactory.getWorld()
                .getGamePane().getChildren().addAll(body, label);
    }

    @Override
    public List<Objective> getObjective() {
        return new ArrayList<Objective>() {
            {
                add(Objective.ATTACK_DEFENSE);
            }
        };
    }

    @Override
    public int getSize() {
        return 20;
    }
    
    @Override
    public void destroy() {
        super.destroy();
        World world = SyndromeFactory.getWorld();
        world.getGamePane().getChildren().removeAll(body, label);
    }
    
    @Override
    public void handleCollision(Projectile projectile) {
    }
    
    private void attack() {
        if(target == null || !SyndromeFactory.getWorld().getNPCs().contains(target)) {
            target = findTarget();
        } else {
            super.moveToward(target);
            if(target.getLocation().distanceTo(location) <= 3) {
                double damage = 0.1 * (SyndromeFactory.getWorld()
                        .getPlayer().getLevel() / 3) + 0.1;
                if(damage > 3) {
                    damage = 3;
                }
                target.inflictDamage(damage);
            }
        }
    }
    
    private void updateSpeed() {
        double newSpeed = 0.25 * (SyndromeFactory.getWorld()
                        .getPlayer().getLevel() / 6) + 0.2;
        if(newSpeed > 1.5D) {
            newSpeed = 1.5D;
        }
        super.speed = newSpeed;
    }
    
    private NPC findTarget() {
        List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
        Collections.shuffle(activeNPCs);
        for(NPC npc : activeNPCs) {
            if(!(npc instanceof Bacteria) 
                    && !(npc instanceof KupfferCell)) {
                return npc;
            }
        }
        return null;
    }
}
