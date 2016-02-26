package syndrome.entity.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.entity.objective.Objective;
import syndrome.logic.World;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.logic.projectile.impl.Halt;
import syndrome.logic.projectile.impl.HealCell;
import syndrome.other.SyndromeFactory;

/**
 * Represents a sinusoidal endothelial Cell.
 * 
 * The function is to produce <b>stronger</b> cells.
 * Destroying endothelial cells makes other cells weaker.
 * 
 * @author Axel Wallin
 */

public class EndothelialCell extends NPC {

    private Circle body;
    private Text label;
    private long lastUpdate;
    
    private Objective action;
    
    /**
     * Creates a new endothelial cell instance to the specified location.
     * 
     * @param location the location where to spawn this instance.
     */
    public EndothelialCell(Location location) {
        super(location);
        super.speed = 0;
        super.health = 100;
        
        this.lastUpdate = 0;
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
        
        long interval = 100000000 / ((SyndromeFactory.getWorld()
                .getPlayer().getLevel() / 2) + 1);
        if(now - lastUpdate > interval) {
            checkActionStatus(); 
            lastUpdate = now;
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
        World world = SyndromeFactory.getWorld();
        world.getGamePane().getChildren().removeAll(body, label);
        world.getPlayer().addPoints(50);
    }

    @Override
    public void render() {
        body.setFill(Paint.valueOf(Color.BLANCHEDALMOND.toString()));
        body.setRadius(15.0F);
        label.setText("E");
        label.setFont(Font.font("8BIT WONDER", 14));
        label.setFill(Paint.valueOf("white"));
        SyndromeFactory.getWorld()
                .getGamePane().getChildren().addAll(body, label);
    }
    
    @Override
    public int getSize() {
        return (int) body.getRadius();
    }

    @Override 
    public List<Objective> getObjective() {
        return new ArrayList<Objective>() {
            {
                add(Objective.REINFORCE);
                add(Objective.ATTACK_PLAYER);
            }
        };
    }

    @Override
    public void handleCollision(Projectile projectile) {
        if(!(projectile instanceof HealCell)) {
            super.health -= 20;
            if(super.health <= 0) {
                SyndromeFactory.getWorld().getPlayer().addPoints(10);
            }
        }
    }

    private void checkActionStatus() {
        Player player = SyndromeFactory.getWorld().getPlayer();
        if(action == null) {
            action = getObjective().get(0);
            if(location.distanceTo(player.getLocation()) < 150
                    && new Random().nextInt(3) == 1) {
                action = getObjective().get(1);
            }
        }
        handleAction(action, player);
    }
    
    private void handleAction(Objective action, Player player) {
        switch(action) {
            case REINFORCE:
                reinforceDefense();
                break;
            case ATTACK_PLAYER:
                attackPlayer(player);
                break;
        }
    }
    
    private void reinforceDefense() {
        NPC target = findTarget();
        if(target != null) {
            HealCell projectile = new HealCell(super.location, target);
            projectile.fire();
            SyndromeFactory.getWorld().addProjectile(projectile, true);
        }
    }
    
    private void attackPlayer(Player player) {
        Halt projectile = new Halt(super.location);
        projectile.fire();
        SyndromeFactory.getWorld().addProjectile(projectile, true);
        if(player.getLevel() > 10) {
            if(player.getSpeed() == 0.25) {
                action = Objective.REINFORCE;
            }
        }
    }
    
    private NPC findTarget() {
        List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
        Collections.shuffle(activeNPCs);
        for(NPC npc : activeNPCs) {
            if(!(npc instanceof Bacteria)) {
                return npc;
            }
        }
        return null;
    }

}
