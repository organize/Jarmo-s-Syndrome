package syndrome.logic.entity;

import syndrome.logic.map.Location;

/**
 *
 * @author Axel Wallin
 */
public class Player extends Entity {
    
    private Direction direction = Direction.NONE;
    
    public Player() {
        super(new Location(300, 300));
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
        System.out.println("DIRECTION=" + direction);
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
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
