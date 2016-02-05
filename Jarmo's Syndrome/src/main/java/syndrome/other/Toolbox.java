package syndrome.other;

import javafx.scene.control.Control;
import syndrome.logic.map.Location;

public class Toolbox {
    
    public void addStylesheet(Control node, String stylesheet) {
        node.getStylesheets().add(getClass().getResource("/resources/css/" + stylesheet + ".css").toExternalForm());
    }
    
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