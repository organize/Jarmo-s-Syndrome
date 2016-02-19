package syndrome.logic.entity;

import syndrome.logic.map.Location;

/**
 * Represents a game entity.
 * 
 * @author Axel Wallin
 */
public abstract class Entity {
    
    protected Location location;
    
    /**
     * Creates a new entity to coordinates [0,0]. 
     */
    public Entity() {
        this(new Location());
    }
    
    /**
     * Creates a new entity to the location supplied.
     * 
     * @param location, the location where this entity should be spawned.
     */
    public Entity(Location location) {
        this.location = location;
    }
    
    public abstract Location getLocation();
    public abstract void setLocation(double x, double y);
    
    public abstract double getRotation();
    public abstract void setRotation(double degrees);
    
    public abstract EntityType getType();
    
    public abstract void destroy();
    
    /**
     * As long as the game is not paused,
     * an instance of <b>SyndromeTimer</b> will call this method
     * for every living entity in the world (60 times / second).
     */
    public abstract void tick();
    
    /**
     * Returns the bounds of this entity represented as
     * an array of coordinates, forming a rectangle.
     * 
     * @return rectangular representation of the bounds.
     */
    public abstract Location[] getBounds();
    
    /**
     * Checks if this entity collides with another entity.
     * @param other, the entity we want to check.
     * 
     * @return whether or not a collision is occuring.
     */
    public abstract boolean collidesWith(Entity other);
    
    public abstract int getSize();
   
}
