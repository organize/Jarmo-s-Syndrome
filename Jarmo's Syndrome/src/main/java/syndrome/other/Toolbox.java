package syndrome.other;

import javafx.scene.Parent;
import syndrome.entity.NPC;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;

/**
 * Contains various useful all-around methods, such as
 * math.
 * 
 * @todo {might remove this class}
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
    public int[] directionToDelta(Direction direction) {
        int[] delta = new int[2];
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
        return delta;
    }
    
    public double[] angleToVelocity(double angle) {
        return new double[]{Math.cos(angle), Math.sin(angle)};
    }
    
    public int countActive(Class<?> target) {
        int count = 0;
        for(NPC npc : SyndromeFactory.getWorld().getNPCs()) {
            if(npc.getClass().isAssignableFrom(target)) {
                count++;
            }
        }
        return count;
    }
}