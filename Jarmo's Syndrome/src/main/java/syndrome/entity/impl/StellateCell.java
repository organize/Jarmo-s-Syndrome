package syndrome.entity.impl;

import java.util.ArrayList;
import java.util.List;
import syndrome.entity.NPC;
import syndrome.entity.objective.Objective;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;

/**
 * Represents a stellate cell.
 * 
 * The function is to store vitamin A.
 * By destroying these cells, the player can demolish 
 * Jarmo's immune system through vitamin A defiency, so
 * antibodies become hard to produce.
 * 
 * {@todo}
 * @author Axel Wallin
 */

public class StellateCell extends NPC {

    /**
     * Creates a new stellate cell instance to the specified location.
     * 
     * @param location the location where to spawn this instance.
     */
    public StellateCell(Location location) {
        super(location);
    }
    
    @Override
    public void tick(long now) {
        
    }

    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Objective> getObjective() {
        return new ArrayList<Objective>() {
            {
                add(Objective.STORE_NUTRIENT);
            }
        };
    }

    @Override
    public void handleCollision(Projectile projectile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
