package syndrome;

import java.awt.Dimension;

/**
 *
 * @author Axel Wallin
 */
public class GameSettings {
    
    private Dimension resolution;
    
    public GameSettings() {
        this.resolution = new Dimension(1024, 768);
    }
    
    public Dimension getResolution() {
        return resolution;
    }
    
    public void setResolution(int width, int height) {
        this.resolution = new Dimension(width, height);
    }
    
}
