package syndrome.logic.map;

import java.awt.geom.Point2D;
import java.util.Random;
import javafx.scene.input.MouseEvent;
import syndrome.entity.Player;
import syndrome.other.SyndromeFactory;

/**
 * Represents a point in the game screen.
 * 
 * @author Axel Wallin
 */
public class Location {
    
    private double x, y;
    
    /**
     * Creates a new location with the coordinates [0, 0].
     */
    public Location() {
        this(0.0D, 0.0D);
    }
    
    /**
     * Creates a new location with the supplied coordinates.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    /**
     * Finds the distance between two Location instances.
     * 
     * @param location the location that needs to be checked.
     * @return distance the distance between this and <i>location</i>.
     */
    public double distanceTo(Location location) {
        return Point2D.distance(x, y, location.getX(), location.getY());
    }
    
    /**
     * Creates a new Location instance that is between
     * this and <i>location</i>.
     * 
     * @param location the other location.
     * @return midpoint the point between this instance and <i>location</i>.
     */
    public Location midpoint(Location location) {
        double middleX = (x + location.getX()) / 2;
        double middleY = (y + location.getY()) / 2;
        return new Location(middleX, middleY);
    }
    
    /**
     * Transforms the coordinates of this location to the
     * parameters supplied.
     * 
     * @param x the x axis value.
     * @param y the y axis value.
     */
    public void transform(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Checks whether or not the destination location 
     * after applying delta changes is within bounds or not,
     * and reverses the direction of the velocity if out of bounds.
     * 
     * @param delta the velocity as coordinate changes.
     * @return a double array which is either identical to the parameter
     *      or the opposite of the parameter.
     */
    public double[] ensureWithinBounds(double[] delta) {
        if(x + delta[0] > 300 || x + delta[0] < -300) {
            delta[0] = -delta[0];
        }
        if(y + delta[1] > 220 || y + delta[1] < -220) {
            delta[1] = -delta[1];
        }
        return delta;
    }
    
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Location)) {
            return false;
        }
        Location location = (Location) other;
        return this.x == location.getX() && this.y == location.getY();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }
    
    /**
     * Creates a new location instance from a mouse event.
     * @param event the MouseEvent instance.
     * @return Location, the corresponding location in game.
     */
    public static Location toLocation(MouseEvent event) {
        return new Location(event.getSceneX() - 320, event.getSceneY() - 240);
    }
    
    /**
     * Creates a new location, randomly.
     * The new location cannot be near the player or out of bounds.
     * 
     * @return Location, a new location instance generated randomly.
     */
    public static Location createRandomLocation() {
        Random random = new Random();
        Player player = SyndromeFactory.getWorld().getPlayer();
        int randX = random.nextInt(320);
        int randY = random.nextInt(240);
        if(random.nextInt(2) == 1) {
            randX = -randX;
        }
        if(random.nextInt(2) == 1) {
            randY = -randY;
        }
        Location result = new Location(randX, randY);
        if(result.distanceTo(player.getLocation()) < 30) {
            result.transform(randX + random.nextInt(25) + 15, randY + random.nextInt(25) + 15);
        }
        if(Math.abs(result.getX()) > 320) {
            result.transform(randX - random.nextInt(25) + 25, randY);
        }
        if(Math.abs(result.getY()) > 240) {
            result.transform(randX, randY - random.nextInt(25) + 25);
        }
        return result;
    }

}