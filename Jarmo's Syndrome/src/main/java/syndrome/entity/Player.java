package syndrome.entity;

import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;
import syndrome.other.Toolbox;

/**
 * A class that represents the player character.
 * 
 * @see {@Entity} for documentation.
 * @author Axel Wallin
 */
public class Player extends Entity {
    
    private Direction direction;
    private double rotation;
    
    private int points;
    private Location lastMousePosition;
    
    public Player() {
        super(new Location(0, 0));
        this.rotation = 0.0D;
        this.direction = Direction.NONE;
        this.points = 0;
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
        return EntityType.PLAYER;
    }

    @Override
    public void tick() {
        if(!direction.equals(Direction.NONE)) {
            handleMovement();
            updateRotation(lastMousePosition);
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
        bounds[1] = new Location(location.getX() + 8, 
                location.getY());
        bounds[2] = new Location(location.getX(), 
                location.getY() + 8);
        bounds[3] = new Location(location.getX() + 8, 
                location.getY() + 8);
        return bounds;
    }
    
    @Override
    public boolean collidesWith(Entity other) {
        java.awt.Rectangle bounds = new java.awt.Rectangle(
                (int) other.getBounds()[0].getX(),
                (int) other.getBounds()[0].getY(),
                other.getSize(), other.getSize());
        
        java.awt.Rectangle ownBounds = new java.awt.Rectangle(
                (int) this.getBounds()[0].getX(),
                (int) this.getBounds()[0].getY(),
                this.getSize(), this.getSize());
        return ownBounds.contains(bounds);
    }
    
    @Override
    public int getSize() {
        return 8;
    }
     
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void updateRotation(Location towards) {
        double angleDegrees = SyndromeFactory.getToolbox().calculateRotation(towards);
        setRotation(angleDegrees);
        this.lastMousePosition = towards;
    }
            
    private void handleMovement() {
        Toolbox toolbox = SyndromeFactory.getToolbox();
        int[] delta = toolbox.directionToDelta(direction);
        setLocation(location.getX() + delta[0], location.getY() + delta[1]);
    }

    @Override
    public void destroy() {

    }
    
    public Location getLastMousePosition() {
        return lastMousePosition;
    }

    public Direction getDirection() {
        return direction;
    }
}
