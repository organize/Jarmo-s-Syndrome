package syndrome.other.input;

import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.entity.impl.*;
import syndrome.logic.map.Location;
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
        handlePlayerRotation(event);
    }
    
    private void handleMouseClick(MouseEvent event) {
        Projectile[] multiEnt = {
            new Entanglement(Location.toLocation(event), 90),
            new Entanglement(Location.toLocation(event), 100),
            new Entanglement(Location.toLocation(event), 80),
        };
        for(Projectile projectile : multiEnt) {
            projectile.fire();
            SyndromeFactory.getWorld().addProjectile(projectile);
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
