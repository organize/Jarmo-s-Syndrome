package syndrome.logic.entity;

import syndrome.logic.map.Location;

public class NPC extends Entity {
    
    private double rotation;

    public NPC() {
        super();
        this.rotation = 0.0d;
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
        this.rotation = degrees;
    }

    @Override
    public double getRotation() {
        return rotation;
    }
    
    @Override
    public Location[] getBounds() {
        Location[] bounds = new Location[4];
        bounds[0] = location;
        bounds[1] = new Location(location.getX() + 4, 
                location.getY());
        bounds[2] = new Location(location.getX(), 
                location.getY() + 4);
        bounds[3] = new Location(location.getX() + 4, 
                location.getY() + 4);
        return bounds;
    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }
    
    @Override
    public int getSize() {
        return 4;
    }

}
