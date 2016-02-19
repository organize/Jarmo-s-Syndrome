package syndrome.logic.projectile;

import javafx.scene.shape.Shape;
import syndrome.logic.map.Axis;

/**
 * Abstract representation of a projectile in the game.
 * 
 * @author Axel Wallin
 */
public interface Projectile {
    
    /**
     * Fires the projectile.
     */
    public void fire();
    
    /**
     * Destroys the projectile.
     */
    public void destroy();
    
    /**
     * Translates the current local coordinates to screen coordinates
     * depending on the given <b>Axis</b>.
     * 
     * @param axis the axis to update.
     */
    public void updateTranslation(Axis axis);
    
    /**
     * Get the Shape instance associated with this projectile.
     * 
     * @todo {change to Image2D}
     * @return the shape object of this projectile.
     */
    public Shape getObject();

}
