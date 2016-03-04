package syndrome.other.input;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import syndrome.logic.map.Direction;
import syndrome.entity.Player;
import syndrome.other.SyndromeFactory;

/**
 * A class that listens to keyboard input when bound to a <b>Node</b>.
 * 
 * @note {method names are descriptive, skip doc for now?}
 * @author Axel Wallin
 */
public class KeyboardInput implements EventHandler<KeyEvent> {
    
    /**
     * A list of the currently held keys.
     */
    private final List<KeyCode> held;
    
    /**
     * A list of valid movement keys.
     */
    private final List<KeyCode> valid =
        new ArrayList<KeyCode>() {
            {
                add(KeyCode.A);
                add(KeyCode.W);
                add(KeyCode.D);
                add(KeyCode.S);
            }
        };
    
    /**
     * Creates a new, empty instance of this KeyboardListener
     * wrapper.
     */
    public KeyboardInput() {
        this.held = new ArrayList<>();
    }

    /**
     * Handles all incoming keyboard input.
     * 
     * @param event the key event.
     */
    @Override
    public void handle(KeyEvent event) {
        if(event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            handleKeyReleased(event);
        }
        if(event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            handleKeyPressed(event);
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        if(event.getCode().isLetterKey()) {
            if(held.contains(event.getCode())) {
                held.remove(event.getCode());
            }
            if(!held.isEmpty()) {
                handleMovement(held.get(0));
            }
        }
        if(held.isEmpty()) {
            SyndromeFactory.getWorld().getPlayer().setDirection(Direction.NONE);
        }
    }
    
    private void handleKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();
        if(code.equals(KeyCode.ESCAPE)) {
            SyndromeFactory.getSettings().togglePause();
        }
        if(code.isLetterKey() && valid.contains(code)) {
            handleMovement(event.getCode());
        }
        if(code.equals(KeyCode.P)) {
            SyndromeFactory.getWorld().getPlayer().setLocation(0, 0);
        }
    }
    
    private void handleMovement(KeyCode pressed) {
        Player player = SyndromeFactory.getWorld().getPlayer();
        if(!held.contains(pressed)) {
            held.add(pressed);
        }
        for(Direction direction : Direction.values()) {
            if(direction.equals(Direction.NONE)) {
                continue;
            }
            KeyCode primary = direction.getPrimary();
            KeyCode secondary = direction.getSecondary();
            if(held.size() == 1 && secondary == null) {
                if(primary.equals(pressed)) {
                    player.setDirection(direction);
                    break;
                }
            } else {
                if(held.contains(primary) && held.contains(secondary)) {
                    player.setDirection(direction);
                    break;
                }
            }
        }   
    }
}
