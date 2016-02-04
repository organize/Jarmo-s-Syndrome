package syndrome.logic.entity;

import syndrome.logic.map.Location;

public class NPC extends Entity {

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

    @Override
    public void setRotation(double degrees) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getRotation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
