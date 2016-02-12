package syndrome.other;

import javafx.scene.control.Control;
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
     * @param node, the instance a stylesheet should be added to.
     * @param stylesheet, the filename of the stylesheet.
     */
    public void addStylesheet(Control node, String stylesheet) {
        node.getStylesheets().add(getClass().getResource("/resources/css/" + stylesheet + ".css").toExternalForm());
    }
    
    /**
     * Calculates the player's rotation so that it faces
     * <i>where</i>. 
     * 
     * @param where, the location to which the player should face.
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
    
}