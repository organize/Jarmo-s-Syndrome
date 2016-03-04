package syndrome.entity;

import syndrome.logic.map.Axis;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * A class that represents a non-player character in the game world.
 * 
 * @see {@Entity} for documentation.
 * @author Axel Wallin
 */
public abstract class NPC extends Entity {
    
    /**
     * The rotation (deg), speed and health of this NPC.
     */
    protected double rotation, speed, health;

    /**
     * Creates a new NPC with the default values
     * (rot=0, speed=0, health=0) to the specified location.
     * @param location the location where to spawn this NPC.
     */
    public NPC(Location location) {
        super(location);
        this.rotation = 0.0d;
        this.speed = 0.0d;
        this.health = 0.0d;
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
    public void tick(long now) {
        if(health <= 0) {
            destroy();
        }
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
    public int getSize() {
        return 4;
    }

    @Override
    public void destroy() {
        SyndromeFactory.getWorld().removeNPC(this);
    }
    
    /**
     * Renders the NPC, ie. builds it's components, if any.
     */
    public abstract void render();
    
    /**
     * Handles collision with a projectile.
     * 
     * @param projectile the projectile that is colliding.
     */
    public abstract void handleCollision(Projectile projectile);
    
    /**
     * Updates the translation coordinates relative to the player's
     * position.
     * 
     * @param axis the axis to update.
     */
    public void updateTranslation(Axis axis) {
        Direction direction = SyndromeFactory.getWorld().getPlayer().getDirection();
        double[] deltas = SyndromeFactory.getToolbox().directionToDelta(direction);
        if(axis == Axis.X_AXIS) {
            location.transform(location.getX() - deltas[0], location.getY());
        }
        if(axis == Axis.Y_AXIS) {
            location.transform(location.getX(), location.getY() - deltas[1]);
        }
        if(axis == Axis.X_AND_Y_AXIS) {
            location.transform(location.getX() - deltas[0], location.getY() - deltas[1]);
        }
    }
    
    protected void moveToward(Entity other) {
        Location destination = other.getLocation();
        int deltaX = 0, deltaY = 0;
        if(destination.getX() < location.getX()) {
            deltaX = -1;
        }
        if(destination.getX() > location.getX()) {
            deltaX = 1;
        }
        if(destination.getY() < location.getY()) {
            deltaY = -1;
        }
        if(destination.getY() > location.getY()) {
            deltaY = 1;
        }
        location.transform(location.getX() + (deltaX * speed), 
                location.getY() + (deltaY * speed));
    }
    
    /**
     * Inflicts damage to this NPC.
     * @param damage the amount of damage to inflict.
     */
    public void inflictDamage(double damage) {
        this.health -= damage;
    }

}
