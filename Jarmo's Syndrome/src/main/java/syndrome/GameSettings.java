package syndrome;

import java.awt.Dimension;

/**
 * Represents the user-defined settings and various gamestates
 * (ie. whether the game is paused, for example).
 * 
 * @author Axel Wallin
 */
public class GameSettings {
    
    private Dimension resolution;
    private boolean paused;
    
    /**
     * Creates a new instance with the default resolution of
     * 1024x768 pixels.
     */
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
    
    /**
     * Toggles pause state.
     */
    public void togglePause() {
        this.paused = !paused;
    }
    
    /**
     * Returns the current state of pause.
     * 
     * @return paused, whether or not the game is paused.
     */
    public boolean isPaused() {
        return paused == true;
    }
    
}
