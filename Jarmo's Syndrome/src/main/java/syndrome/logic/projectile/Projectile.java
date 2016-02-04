package syndrome.logic.projectile;

import javafx.scene.shape.Shape;

public interface Projectile {
    
    public void fire();
    
    public void destroy();
    
    public Shape getObject();

}
