package syndrome.logic.entity;

import syndrome.logic.map.Direction;
import java.awt.MouseInfo;
import java.awt.Point;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;

public class Player extends Entity {
    
    private Direction direction;
    private double rotation;
    
    public Player() {
        super(new Location(300, 300));
        this.rotation = 0.0D;
        this.direction = Direction.NONE;
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
        }
        updateRotation();
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
        Location[] bounds = new Location[3];
        bounds[0] = location;
        bounds[1] = new Location(location.getX() + 8, 
                location.getY());
        bounds[2] = new Location(location.getX(), 
                location.getY() + 8);
        bounds[2] = new Location(location.getX() + 8, 
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
    
    private void updateRotation() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        Location mouseLoc = new Location(point.getX(), point.getY());
        double angleDegrees = SyndromeFactory.getToolbox().calculateRotation(mouseLoc);
        setRotation(angleDegrees);
    }
            
    private void handleMovement() {
        switch(direction) {
            case NORTH:
                setLocation(location.getX(), location.getY() - 2);
                break;
            case SOUTH:
                setLocation(location.getX(), location.getY() + 2);
                break;
            case EAST:
                setLocation(location.getX() + 2, location.getY());
                break;
            case WEST:
                setLocation(location.getX() - 2, location.getY());
                break;
            case NORTHEAST:
                setLocation(location.getX() + 2, location.getY() - 2);
                break;
            case NORTHWEST:
                setLocation(location.getX() - 2, location.getY() - 2);
                break;
            case SOUTHEAST:
                setLocation(location.getX() + 2, location.getY() + 2);
                break;
            case SOUTHWEST:
                setLocation(location.getX() - 2, location.getY() + 2);
                break;
        }
    }
}
