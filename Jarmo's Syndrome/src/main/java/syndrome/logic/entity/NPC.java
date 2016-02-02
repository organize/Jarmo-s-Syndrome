package syndrome.logic.entity;

import syndrome.logic.World;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;

/**
 *
 * @author Axel Wallin
 */
public class NPC extends Entity {
    
    private final World world = SyndromeFactory.getWorld();

    public NPC() {
        super();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(double x, double y) {
        location.transform(x, y);
    }

    @Override
    public EntityType getType() {
        return EntityType.NPC;
    }

    @Override
    public void tick() {
        
    }

}
