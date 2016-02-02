package syndrome;

import java.awt.Dimension;

/**
 *
 * @author Axel Wallin
 */
public class GameSettings {
    
    private Dimension resolution;
    private boolean paused;
    
    public GameSettings() {
        this.resolution = new Dimension(1024, 768);
        this.paused = false;
    }
    
    public Dimension getResolution() {
        return resolution;
    }
    
    public void setResolution(int width, int height) {
        this.resolution = new Dimension(width, height);
    }
    
    public void togglePause() {
        this.paused = !paused;
    }
    
    public boolean isPaused() {
        return paused == true;
    }
    
}
