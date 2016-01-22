package syndrome.tools;

import javafx.scene.control.Control;

/**
 *
 * @author Axel Wallin
 */
public class Toolbox {
    
    public void addStylesheet(Control node, String stylesheet) {
        node.getStylesheets().add(getClass().getResource("/resources/css/" + stylesheet + ".css").toExternalForm());
    }
    
}
