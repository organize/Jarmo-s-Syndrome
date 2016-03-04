package syndrome.other.input;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import syndrome.entity.Player;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.projectile.impl.Entanglement;
import syndrome.other.SyndromeFactory;

/**
 * A class that listens to mouse input when bound to a <b>Node</b>.
 * 
 * @note {method names are descriptive, skip doc for now?}
 * @author Axel Wallin
 */
public class MouseInput implements EventHandler<MouseEvent> {

    /**
     * Handles all incoming mouse input.
     * 
     * @param event the mouse event.
     */
    @Override
    public void handle(MouseEvent event) {
        if(!SyndromeFactory.getSettings().isPaused()) {
            if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                handleMouseClick(event);
            }
            handlePlayerRotation(event);
        }
    }
    
    private void handleMouseClick(MouseEvent event) {
        Projectile[] multiEnt = {
            new Entanglement(90),
            new Entanglement(100),
            new Entanglement(80),
        };
        for(Projectile projectile : multiEnt) {
            projectile.fire();
            SyndromeFactory.getWorld().addProjectile(projectile, true);
            if(SyndromeFactory.getWorld().getPlayer().getLevel() < 5) {
                break;
            }
        }
    }
    
    private void handlePlayerRotation(MouseEvent e) {
        Player player = SyndromeFactory.getWorld().getPlayer();
        player.updateRotation(Location.toLocation(e));
    }
}
