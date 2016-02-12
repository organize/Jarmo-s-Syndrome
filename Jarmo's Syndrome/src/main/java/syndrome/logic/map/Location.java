package syndrome.logic.map;

import java.awt.geom.Point2D;

/**
 * Represents a point in the game screen.
 * 
 * @author Axel Wallin
 */
public class Location {
    
    private double x, y;
    
    public Location() {
        this(0.0D, 0.0D);
    }
    
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
     * @param location, the location that needs to be checked.
     * @return distance, the distance between this and <i>location</i>.
     */
    public double distanceTo(Location location) {
        return Point2D.distance(x, y, location.getX(), location.getY());
    }
    
    /**
     * Creates a new Location instance that is between
     * this and <i>location</i>.
     * 
     * @param location, the other location.
     * @return midpoint, the point between this instance and <i>location</i>.
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
     * @param x, the x axis value.
     * @param y, the y axis value.
     */
    public void transform(double x, double y) {
        this.x = x;
        this.y = y;
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

}