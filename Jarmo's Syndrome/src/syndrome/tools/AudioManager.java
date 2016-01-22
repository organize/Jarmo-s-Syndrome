package syndrome.tools;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Axel Wallin
 */
public class AudioManager {
    
    private Map<String, AudioClip> cache;
    
    public AudioManager() {
        this.cache = new HashMap<String, AudioClip>();
    }
    
    public void precache(String identifier, String filename) {
        AudioClip clip = new AudioClip(getClass().getResource("/resources/audio/music/" + filename + ".mp3").toExternalForm());
        clip.setVolume(0.10);
        cache.put(identifier, clip);
    }
    
    public void playCached(String identifier) {
        cache.get(identifier).play();
    }
    
    public void stopCached(String identifier) {
        cache.get(identifier).stop();
    }
    
    public AudioClip get(String identifier) {
        if(!cache.containsKey(identifier)) {
            throw new NullPointerException("identifier " + identifier + " not present in audio cache");
        }
        return cache.get(identifier);
    }
    
    public void playEffect(String filename) {
        new AudioClip(getClass().getResource("/resources/audio/fx/" + filename + ".mp3").toExternalForm()).play();
    }
    
}
