package syndrome.entity.impl;

import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.entity.objective.Objective;
import syndrome.logic.World;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.projectile.impl.Entanglement;
import syndrome.projectile.impl.HealCell;
import syndrome.other.SyndromeFactory;
import syndrome.annotation.ObjectiveInfo;

/**
 * Represents an antibody.
 * 
 * The function is to perform lysis on infected cells
 * and harm the player (virus).
 * Destroying antibodies helps you survive for longer.
 * 
 * @author Axel Wallin
 */

@ObjectiveInfo(objectives = {Objective.ATTACK_PLAYER, Objective.LYSIS})
public class Antibody extends NPC {

    /**
     * The body element of this NPC.
     */
    private final Circle body;
    
    /**
     * The label on top of the body of this NPC.
     */
    private final Text label;
    
    /**
     * Creates a new antibody instance to the specified location.
     * 
     * @param location the location where to spawn this instance.
     */
    public Antibody(Location location) {
        super(location);
        super.speed = 0.5;
        
        this.body = new Circle();
        this.label = new Text();
        this.health = 50;
    }
    
    @Override
    public void tick(long now) {
        super.tick(now);
        
        body.setTranslateX(location.getX());
        body.setTranslateY(location.getY());
        
        body.setRadius(super.health / 3);
        
        label.setTranslateX(location.getX());
        label.setTranslateY(location.getY());
       
        attack();
        
        if(SyndromeFactory.getWorld().getPlayer().getLevel() > 10) {
            super.speed = 1;
        }
    }

    @Override
    public void render() {
        body.setFill(Paint.valueOf(Color.LIGHTGREEN.toString()));
        body.setRadius(7.0F);
        label.setText("A");
        label.setFont(Font.font("8BIT WONDER", 7));
        label.setFill(Paint.valueOf("white"));
        SyndromeFactory.getWorld()
                .getGamePane().getChildren().addAll(body, label);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        World world = SyndromeFactory.getWorld();
        if(world.getGamePane() != null) {
            world.getGamePane().getChildren().removeAll(body, label);
        }
    }
    
    @Override
    public Location[] getBounds() {
        Location[] bounds = new Location[4];
        bounds[0] = location;
        bounds[1] = new Location(location.getX() + body.getRadius(), location.getY());
        bounds[2] = new Location(location.getX(), location.getY() + body.getRadius());
        bounds[3] = new Location(location.getX() + body.getRadius(), location.getY() + body.getRadius());
        return bounds;
    }
    
    @Override
    public int getSize() {
        return (int) body.getRadius();
    }

    @Override
    public void handleCollision(Projectile projectile) {
        if(projectile instanceof Entanglement) {
            super.health -= 5 * SyndromeFactory.getWorld().getPlayer().getLevel();
            if(super.health <= 0) {
                SyndromeFactory.getWorld().getPlayer().addPoints(50);
            }
        }
        if(projectile instanceof HealCell && super.health < 100) {
            super.health += 5 * SyndromeFactory.getWorld().getPlayer().getLevel();
        }
    }
    
    private void attack() {
        Player target = SyndromeFactory.getWorld().getPlayer();
        super.moveToward(target);
        if(location.distanceTo(target.getLocation()) < 30) {
            target.inflictDamage(body.getRadius());
            this.destroy();
        }
    }

}
