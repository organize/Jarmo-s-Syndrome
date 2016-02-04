package syndrome.logic.entity;

import javafx.scene.input.KeyCode;

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
