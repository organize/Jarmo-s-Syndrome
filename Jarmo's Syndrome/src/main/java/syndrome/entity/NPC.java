package syndrome.entity;

import java.util.List;
import syndrome.entity.objective.Objective;
import syndrome.logic.map.Axis;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.logic.projectile.Projectile;
import syndrome.other.SyndromeFactory;

/**
 * A class that represents a non-player character in the game world.
 * 
 * @see {@Entity} for documentation.
 * @author Axel Wallin
 */
public abstract class NPC extends Entity {
    
    protected double rotation, speed, health;

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
    public boolean collidesWith(Entity other) {
        return false;
    }
    
    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public void destroy() {
        SyndromeFactory.getWorld().removeNPC(this);
    }
    
    public abstract void render();
    
    public abstract List<Objective> getObjective();
    
    public abstract void handleCollision(Projectile projectile);
    
    public void updateTranslation(Axis axis) {
        Direction direction = SyndromeFactory.getWorld().getPlayer().getDirection();
        int[] deltas = SyndromeFactory.getToolbox().directionToDelta(direction);
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
    
    public void inflictDamage(double damage) {
        this.health -= damage;
    }

}
