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
