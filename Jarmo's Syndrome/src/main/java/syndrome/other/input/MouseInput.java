package syndrome.other.input;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import syndrome.logic.projectile.Projectile;
import syndrome.logic.projectile.impl.Entanglement;
import syndrome.other.SyndromeFactory;

/**
 * A class that listens to mouse input when bound to a <b>Node</b>.
 * 
 * @note {method names are descriptive, skip doc for now?}
 * @author Axel Wallin
 */
public class MouseInput implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            handleMouseClick(event);
        }
    }
    
    private void handleMouseClick(MouseEvent event) {
        Pane gamePane = SyndromeFactory.getWorld().getGamePane();
        Projectile entanglement = new Entanglement();
        gamePane.getChildren().add(entanglement.getObject());
        entanglement.fire();
    }

}
