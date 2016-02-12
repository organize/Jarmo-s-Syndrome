package syndrome.logic.map;

import javafx.scene.input.KeyCode;

/**
 * An enumeration that holds all possible directions and the
 * associated key combinations for the player's movement.
 * 
 * @author Axel Wallin
 */
public enum Direction {
    
    NONE(null),
    
    NORTH(KeyCode.W),
    EAST(KeyCode.D),
    SOUTH(KeyCode.S),
    WEST(KeyCode.A),
    
    NORTHEAST(KeyCode.W, KeyCode.D),
    NORTHWEST(KeyCode.W, KeyCode.A),
    SOUTHEAST(KeyCode.S, KeyCode.D),
    SOUTHWEST(KeyCode.S, KeyCode.A);
     
    private final KeyCode primary, secondary;
    
    Direction(KeyCode primary, KeyCode secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }
    
    Direction(KeyCode primary) {
        this(primary, null);
    }
    
    public KeyCode getPrimary() {
        return primary;
    }
    
    public KeyCode getSecondary() {
        return secondary;
    }
   
}
