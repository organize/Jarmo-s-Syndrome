package syndrome.ui.impl;

import java.awt.Dimension;
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
import syndrome.other.SyndromeFactory;
import syndrome.ui.MenuButton;
import syndrome.ui.SyndromeGUI;

/**
 *
 * @author Axel Wallin
 */
public class MainMenu implements SyndromeGUI {
    
    private Stage stage;
    private Pane root;

    @Override
    public void show() {
        Dimension resolution = SyndromeFactory.getSettings().getResolution();
        stage.setScene(new Scene(root, resolution.getWidth(), resolution.getHeight()));
        stage.show();
    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void destroy() {
        root.setVisible(false);
        stage.close();
    }

    @Override
    public void build(Stage stage) {
        MenuButton newGame = new MenuButton(" New Game ", "white");
        newGame.setOnAction((ActionEvent event) -> {
            SyndromeFactory.getGUIManager().hideMainMenu();
            SyndromeFactory.getGUIManager().drawGameScreen();
        });
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
        
        Pane paneRoot = new Pane();
        paneRoot.setStyle("-fx-background-color: #000000;");
        paneRoot.getChildren().add(newGame);
        paneRoot.getChildren().add(quit);
        paneRoot.getChildren().add(mainTitle);
        paneRoot.getChildren().add(createAudioControls());
        
        this.stage = stage;
        this.root = paneRoot;
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

}