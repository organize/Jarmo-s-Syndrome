package syndrome.logic.projectile;

import javafx.scene.shape.Shape;

/**
 * Abstract representation of a projectile in the game.
 * 
 * @author Axel Wallin
 */
public interface Projectile {
    
    public void fire();
    
    public void destroy();
    
    /**
     * Get the Shape instance associated with this projectile.
     * 
     * @todo {change to Image2D}
     * @return the shape object of this projectile.
     */
    public Shape getObject();

}
