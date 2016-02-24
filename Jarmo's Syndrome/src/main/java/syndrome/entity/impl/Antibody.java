package syndrome.entity.impl;

import com.sun.javafx.scene.traversal.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import syndrome.entity.NPC;
import syndrome.entity.objective.Objective;
import syndrome.logic.World;
import syndrome.logic.map.Location;
import syndrome.logic.projectile.Projectile;
import syndrome.logic.projectile.impl.Entanglement;
import syndrome.logic.projectile.impl.HealCell;
import syndrome.other.SyndromeFactory;

/**
 * Represents an antibody.
 * 
 * The function is to perform lysis on infected cells
 * and harm the player (virus).
 * Destroying antibodies helps you survive for longer.
 * 
 * @author Axel Wallin
 */

public class Antibody extends NPC {

    private Circle body;
    private Text label;
    
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
    }

    @Override
    public void render() {
        body.setFill(Paint.valueOf(Color.LIGHTGREEN.toString()));
        body.setRadius(7.0F);
        label.setText("A");
        label.setFont(Font.font("8BIT WONDER", 6));
        label.setFill(Paint.valueOf("white"));
        SyndromeFactory.getWorld()
                .getGamePane().getChildren().addAll(body, label);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        World world = SyndromeFactory.getWorld();
        world.getGamePane().getChildren().removeAll(body, label);
        world.getPlayer().addPoints(50);
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
    public List<Objective> getObjective() {
        return new ArrayList<Objective>() {
            {
                add(Objective.ATTACK_PLAYER);
                add(Objective.LYSIS);
            }
        };
    }
    
    @Override
    public int getSize() {
        return (int) body.getRadius();
    }

    @Override
    public void handleCollision(Projectile projectile) {
        if(projectile instanceof Entanglement) {
            super.health -= 5 * SyndromeFactory.getWorld().getPlayer().getLevel();
        }
        if(projectile instanceof HealCell && super.health < 100) {
            super.health += 2.5 * SyndromeFactory.getWorld().getPlayer().getLevel();
        }
    }
    
    private void attack() {
        super.moveToward(SyndromeFactory.getWorld().getPlayer());
    }

}
