package syndrome.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Axel Wallin
 */
public class AudioManager {
    
    private final Map<String, AudioClip> cache;
    
    public AudioManager() {
        this.cache = new HashMap<>();
    }
    
    public void precache(String identifier, String filename) {
        if(filename == null) {
            throw new NullPointerException("filename cannot be null");
        }
        try {
            String path = getClass().getResource("/resources/audio/music/" + filename + ".mp3").toExternalForm();
            AudioClip clip = new AudioClip(path);
            clip.setVolume(0.10);
            cache.put(identifier, clip);
        } catch(NullPointerException exception) {
            throw new AssertionError("file " + filename + ".mp3 not found");
        }
    }
    
    public void playCached(String identifier) {
        if(!cache.containsKey(identifier)) {
            throw new AssertionError("identifier " + identifier + " not present in audio cache");
        }
        cache.get(identifier).play();
    }
    
    public void stopCached(String identifier) {
        if(!cache.containsKey(identifier)) {
            throw new AssertionError("identifier " + identifier + " not present in audio cache");
        }
        cache.get(identifier).stop();
    }
    
    public AudioClip get(String identifier) {
        if(!cache.containsKey(identifier)) {
            throw new AssertionError("identifier " + identifier + " not present in audio cache");
        }
        return cache.get(identifier);
    }
    
    public void playEffect(String filename) {
        if(filename == null) {
            throw new NullPointerException("filename cannot be null");
        }
        try {
            new AudioClip(getClass().getResource("/resources/audio/fx/" + filename + ".mp3").toExternalForm()).play();
        } catch(NullPointerException e) {
            throw new AssertionError("file " + filename + ".mp3 not found");
        }
    }
    
}
