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
    
    /**
     * The main Toolbox instance.
     */
    private static Toolbox toolbox;
    
    /**
     * The main AudioManager instance.
     */
    private static AudioManager audioManager;
    
    /**
     * The main GUIManager instance.
     */
    private static GameSettings settings;
    
    /**
     * The main GUIManager instance.
     */
    private static GUIManager guiManager;
    
    /**
     * The main World instance.
     */
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
    
    /**
     * Resets all instances the class currently holds,
     * should only be called if the game has ended.
     */
    public static void resetAll() {
        world = null;
        guiManager = null;
        settings = null;
        audioManager = null;
        toolbox = null;
    }
}
