package syndrome;

import javafx.application.Application;
import javafx.stage.Stage;
import syndrome.other.SyndromeFactory;

/**
 * The main class and program entry-point.
 * @author Axel Wallin
 */
public class Syndrome extends Application {
    
    /**
     * Method to set-up initial menu and audio effects.
     * Should <i>only</i> be called from the superclass (Application).
     * @param primaryStage, an automatically created Stage instance.
     */
    @Override
    public void start(Stage primaryStage) {
        SyndromeFactory.getAudioManager().precache("title", "title");
        SyndromeFactory.getGUIManager().drawMainMenu();
    }
    
    /**
     * Program entry-point.
     * Currently, the only accepted parameter is resolution (widthxheight).
     * @param args, command-line arguments.
     */
    public static void main(String[] args) {
        if(args.length != 0) {
            String preferredResolution = args[0];
            int width = Integer.parseInt(preferredResolution.split("x")[0]);
            int height = Integer.parseInt(preferredResolution.split("x")[1]);
            SyndromeFactory.getSettings().setResolution(width, height);
        }
        launch(args);
    }
    
}
