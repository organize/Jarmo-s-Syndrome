package syndrome.other;

import syndrome.GameSettings;
import syndrome.logic.World;
import syndrome.ui.GUIManager;

/**
 * The factory class for all one-instance only-classes.
 * Makes sure there will never exist more than one instance
 * of certain critical classes.
 * 
 * @author Axel Wallin
 */
public class SyndromeFactory {
    
    private static Toolbox toolbox;
    private static AudioManager audioManager;
    private static GameSettings settings;
    private static GUIManager guiManager;
    private static World world;
    
    public static Toolbox getToolbox() {
        if(toolbox == null) {
            toolbox = new Toolbox();
        }
        return toolbox;
    }
    
    public static AudioManager getAudioManager() {
        if(audioManager == null) {
            audioManager = new AudioManager();
        }
        return audioManager;
    }
    
    public static GameSettings getSettings() {
        if(settings == null) {
            settings = new GameSettings();
        }
        return settings;
    }

    public static GUIManager getGUIManager() {
        if(guiManager == null) {
            guiManager = new GUIManager();
        }
        return guiManager;
    }
    
    public static World getWorld() {
        if(world == null) {
            world = new World();
        }
        return world;
    }
    
}
