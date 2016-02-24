package syndrome.entity.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import syndrome.logic.projectile.Projectile;
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
        
        checkActionStatus();
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
        }
    }

    private void checkActionStatus() {
        Player player = SyndromeFactory.getWorld().getPlayer();
        Objective action = getObjective().get(1);
        if(location.distanceTo(player.getLocation()) > 100) {
            action = getObjective().get(0);
        }
        //handleAction(action);
        reinforceDefense();
    }
    
    private void handleAction(Objective action) {
        switch(action) {
            case REINFORCE:
                reinforceDefense();
                break;
            case ATTACK_PLAYER:
                attackPlayer();
                break;
        }
    }
    
    private void reinforceDefense() {
        NPC target = findTarget();
        if(target != null) {
            HealCell projectile = new HealCell(super.location, target);
            projectile.fire();
            SyndromeFactory.getWorld().addProjectile(projectile);
        }
    }
    
    private void attackPlayer() {
        
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
