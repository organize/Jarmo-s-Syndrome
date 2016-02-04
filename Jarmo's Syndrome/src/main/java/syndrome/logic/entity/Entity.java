package syndrome.logic.entity;

import syndrome.logic.map.Location;

public abstract class Entity {
    
    protected Location location;
    
    public Entity() {
        this(new Location());
    }
    
    public Entity(Location location) {
        this.location = location;
    }
    
    public abstract Location getLocation();
    public abstract void setLocation(double x, double y);
    
    public abstract double getRotation();
    public abstract void setRotation(double degrees);
    
    public abstract EntityType getType();
    public abstract void tick();
    
    public enum EntityType {
        NPC,
        PLAYER,
        OTHER;
    }

}
