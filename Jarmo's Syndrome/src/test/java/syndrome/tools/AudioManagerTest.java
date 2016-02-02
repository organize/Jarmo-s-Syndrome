package syndrome.tools;

import syndrome.other.AudioManager;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AudioManagerTest {
    
    @Test(expected = NullPointerException.class)
    public void testNullPrecache() {
        AudioManager manager = new AudioManager();
        manager.precache("blahblah", null);
    }
    
    @Test(expected = AssertionError.class)
    public void testNotFoundPrecache() {
        AudioManager manager = new AudioManager();
        manager.precache("blahblah", "nonexisting");
    }
    
    @Test(expected = AssertionError.class)
    public void testPlay() {
        AudioManager manager = new AudioManager();
        manager.playCached("abc");
    }
    
    @Test(expected = AssertionError.class)
    public void testStop() {
        AudioManager manager = new AudioManager();
        manager.stopCached("abc");
    }
    
    @Test(expected = AssertionError.class)
    public void testGet() {
        AudioManager manager = new AudioManager();
        manager.stopCached("abc");
    }
    
    @Test(expected = AssertionError.class)
    public void testPlayEffectMissing() {
        AudioManager manager = new AudioManager();
        manager.playEffect("abc");
    }
    
    @Test(expected = NullPointerException.class)
    public void testPlayEffectNull() {
        AudioManager manager = new AudioManager();
        manager.playEffect(null);
    }
}