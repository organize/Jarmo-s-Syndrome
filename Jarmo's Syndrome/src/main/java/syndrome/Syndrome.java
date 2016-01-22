package syndrome;

import java.awt.Dimension;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import syndrome.tools.SyndromeFactory;
import syndrome.ui.MenuButton;

/**
 * 
 * @author Axel Wallin
 */
public class Syndrome extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        SyndromeFactory.getAudioManager().precache("title", "title");
        
        MenuButton newGame = new MenuButton(" New Game ", "white");
        newGame.relocate(400, 225);
        
        MenuButton quit = new MenuButton("      EXIT      ", "white");
        quit.relocate(400, 280);
        quit.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });
        
        Text mainTitle = new Text(200, 150, "Jarmo's Syndrome");
        mainTitle.setFont(Font.font ("8BIT WONDER", 40));
        mainTitle.setFill(Paint.valueOf("white")); 
        mainTitle.setStyle("-fx-effect: dropshadow(gaussian, red, 7, 0.0, 2, 2);");
        
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #000000;");
        root.getChildren().add(newGame);
        root.getChildren().add(quit);
        root.getChildren().add(mainTitle);
        root.getChildren().add(createAudioControls());
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Jarmo's Syndrome");
        Dimension resolution = SyndromeFactory.getSettings().getResolution();
        primaryStage.setScene(new Scene(root, resolution.getWidth(), resolution.getHeight()));
        primaryStage.show();
    }
    
    private Button createAudioControls() {
        Button audioStatus = new Button();
        Image image = new Image(getClass().getResourceAsStream("/resources/images/audio_muted.png"));
        audioStatus.setGraphic(new ImageView(image));
        audioStatus.relocate(5, 705);
        audioStatus.setPadding(Insets.EMPTY);
        audioStatus.setStyle("-fx-background-color: transparent;");
        audioStatus.setOnAction((ActionEvent event) -> {
            AudioClip clip = SyndromeFactory.getAudioManager().get("title");
            if(clip.isPlaying()) {
                clip.stop();
                Image muted = new Image(getClass().getResourceAsStream("/resources/images/audio_muted.png"));
                audioStatus.setGraphic(new ImageView(muted));
            } else {
                clip.play();
                Image unmuted = new Image(getClass().getResourceAsStream("/resources/images/audio_enabled.png"));
                audioStatus.setGraphic(new ImageView(unmuted));
            }
        });
        return audioStatus;
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
