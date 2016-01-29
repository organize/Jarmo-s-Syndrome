package syndrome.logic;

import syndrome.logic.map.Location;

public abstract class Entity {
    
    private Location location;
    
    public Entity() {
        this.location = new Location();
    }

}
