package syndrome.entity;

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
     * @param location the location where this entity should be spawned.
     */
    public Entity(Location location) {
        this.location = location;
    }
    
    /**
     * Get the location of the entity.
     * 
     * @return location the location of this entity.
     */
    public abstract Location getLocation();
    
    /**
     * Sets the location of the entity.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public abstract void setLocation(double x, double y);
    
    /**
     * Get the rotation of the entity.
     * 
     * @return rotation the current rotation in degrees.
     */
    public abstract double getRotation();
    
    /**
     * Sets the rotation of the entity.
     * 
     * @param degrees the target rotation.
     */
    public abstract void setRotation(double degrees);
    
    /**
     * Get the type of the entity.
     * 
     * @return the type of this entity.
     */
    public abstract EntityType getType();
    
    /**
     * Destroys this entity.
     */
    public abstract void destroy();
    
    /**
     * As long as the game is not paused,
     * an instance of <b>SyndromeTimer</b> will call this method
     * for every living entity in the world (60 times / second).
     * 
     * @param now the current time in millis.
     */
    public abstract void tick(long now);
    
    /**
     * Returns the bounds of this entity represented as
     * an array of coordinates, forming a rectangle.
     * 
     * @return rectangular representation of the bounds.
     */
    public abstract Location[] getBounds();
    
    
    /**
     * Get the size of the entity. Default is 8 for players, 4 for NPCs.
     * 
     * @return the size.
     */
    public abstract int getSize();
   
}
