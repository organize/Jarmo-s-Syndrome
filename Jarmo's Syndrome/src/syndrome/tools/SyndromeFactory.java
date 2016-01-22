package syndrome.tools;

import syndrome.GameSettings;

/**
 *
 * @author Axel Wallin
 */
public class SyndromeFactory {
    
    private static Toolbox toolbox;
    private static AudioManager audioManager;
    private static GameSettings settings;
    
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
    
}
