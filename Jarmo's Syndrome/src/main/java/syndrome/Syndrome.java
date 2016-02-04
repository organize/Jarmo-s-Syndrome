package syndrome;

import javafx.application.Application;
import javafx.stage.Stage;
import syndrome.other.SyndromeFactory;

public class Syndrome extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        SyndromeFactory.getAudioManager().precache("title", "title");
        SyndromeFactory.getGUIManager().drawMainMenu();
    }
    
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
