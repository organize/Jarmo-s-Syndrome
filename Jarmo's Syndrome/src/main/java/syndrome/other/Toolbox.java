package syndrome.other;

import javafx.scene.Parent;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;

/**
 * Contains various useful all-around methods, such as
 * math.
 * 
 * @author Axel Wallin
 */
public class Toolbox {
    
    /**
     * Attaches a stylesheet file to a <b>Control</b> instance.
     * 
     * @param node the instance a stylesheet should be added to.
     * @param stylesheet the filename of the stylesheet.
     */
    public void addStylesheet(Parent node, String stylesheet) {
        node.getStylesheets().add(getClass().getResource("/resources/css/" + stylesheet + ".css").toExternalForm());
    }
    
    /**
     * Calculates the player's rotation so that it faces
     * <i>where</i>. 
     * 
     * @param where the location to which the player should face.
     * @return the rotation in degrees.
     */
    public double calculateRotation(Location where) {
        Location midpoint = SyndromeFactory.getWorld().getPlayer().getLocation();
        double angleRadians = Math.atan2(where.getY() - midpoint.getY(), where.getX() - midpoint.getX());

        double angleDegrees = angleRadians * (180 / Math.PI) + 90;
        if(angleDegrees < 0) {
            angleDegrees += 360;
        }
        return angleDegrees;
    }
    
    /**
     * Turns a Direction into corresponding coordinate changes.
     * 
     * @param direction the direction to transform.
     * @return an integer array with the sufficient changes.
     */
    public double[] directionToDelta(Direction direction) {
        double[] delta = new double[2];
        switch(direction) {
            case NORTH:
                delta[1] = -2;
                break;
            case SOUTH:
                delta[1] = 2;
                break;
            case EAST:
                delta[0] = 2;
                break;
            case WEST:
                delta[0] = -2;
                break;
            case NORTHEAST:
                delta[0] = 2;
                delta[1] = -2;
                break;
            case NORTHWEST:
                delta[0] = -2;
                delta[1] = -2;
                break;
            case SOUTHEAST:
                delta[0] = 2;
                delta[1] = 2;
                break;
            case SOUTHWEST:
                delta[0] = -2;
                delta[1] = 2;
                break;
        }
        double speedModifier = SyndromeFactory.getWorld().getPlayer().getSpeed();
        if(speedModifier != 1) {
            delta[0] = delta[0] * speedModifier;
            delta[1] = delta[1] * speedModifier;
        }
        return delta;
    }
    
    /**
     * Converts an angle to x and y velocity.
     * @param angle the angle, in radians
     * @return a double-array containing the velocities.
     */
    public double[] angleToVelocity(double angle) {
        return new double[]{Math.cos(angle), Math.sin(angle)};
    }
    
    /**
     * Counts active NPCs in the world.
     * @param target the NPCs to count.
     * @return the amount of active NPCs assignable from the target.
     */
    public int countActive(Class<?> target) {
        int count = 0;
        return SyndromeFactory.getWorld().getNPCs()
                .stream()
                .filter((npc) -> (npc.getClass().isAssignableFrom(target)))
                .map((instance) -> 1)
                .reduce(count, Integer::sum);
    }
}