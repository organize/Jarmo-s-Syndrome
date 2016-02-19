package syndrome.other;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;

/**
 * Handles all things audio-related.
 * 
 * @author Axel Wallin
 */

public class AudioManager {
    
    private final Map<String, AudioClip> cache;
    
    
    /**
     * Creates an instance of AudioManager with empty cache.
     */
    public AudioManager() {
        this.cache = new HashMap<>();
    }
    
    /**
     * Precaches an audio file so it can be rapidly accessed in the future.
     * 
     * @param identifier the key for this audio instance.
     * @param filename the filename where the audio is located.
     */
    public void precache(String identifier, String filename) {
        if(filename == null) {
            throw new NullPointerException("filename cannot be null");
        }
        try {
            URL resource = getClass().getResource("/resources/audio/music/" + filename + ".mp3");
            AudioClip clip = new AudioClip(resource.toExternalForm());
            clip.setVolume(0.10);
            cache.put(identifier, clip);
        } catch(NullPointerException exception) {
            throw new AssertionError("file " + filename + ".mp3 not found");
        }
    }
    
    /**
     * Plays an audio file if present in the current cache.
     * 
     * @param identifier the key of the wanted audio instance.
     */
    public void playCached(String identifier) {
        if(!cache.containsKey(identifier)) {
            throw new AssertionError("identifier " + identifier + " not present in audio cache");
        }
        cache.get(identifier).play();
    }
    
    /**
     * Stops an audio file if present in the current cache.
     * 
     * @param identifier the key of the wanted audio instance.
     */
    public void stopCached(String identifier) {
        if(!cache.containsKey(identifier)) {
            throw new AssertionError("identifier " + identifier + " not present in audio cache");
        }
        cache.get(identifier).stop();
    }
    
    /**
     * Gets an audio file if present in the current cache.
     * 
     * @todo {convert into <i>Optional<AudioClip></i>}
     * @param identifier the key of the wanted audio instance.
     * @return an audio clip if present.
     */
    public AudioClip get(String identifier) {
        if(!cache.containsKey(identifier)) {
            throw new AssertionError("identifier " + identifier + " not present in audio cache");
        }
        return cache.get(identifier);
    }
    
    /**
     * Plays an audio effect.
     * 
     * @note this method does not use the cache, rather it will
     *   simply play a one-shot of the audio file, if it exists.
     * @param filename the audio effect filename.
     */
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
