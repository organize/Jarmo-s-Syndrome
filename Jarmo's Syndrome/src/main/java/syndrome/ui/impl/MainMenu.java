package syndrome.ui.impl;

import java.awt.Dimension;
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
        newGame.setOnAction((e) -> {
            SyndromeFactory.getGUIManager().hideMainMenu();
            SyndromeFactory.getGUIManager().drawGameScreen();
            SyndromeFactory.getWorld().startTimer();
        });
        newGame.relocate(230, 170);
        
        MenuButton quit = new MenuButton("      EXIT      ", "white");
        quit.relocate(230, 230);
        quit.setOnAction((e) -> {
            System.exit(0);
        });
        
        Text mainTitle = new Text(90, 120, "Jarmo's Syndrome");
        mainTitle.setFont(Font.font ("8BIT WONDER", 30));
        mainTitle.setFill(Paint.valueOf("white")); 
        mainTitle.setStyle("-fx-effect: dropshadow(gaussian, red, 7, 0.0, 2, 2);");
        
        Pane groupRoot = new Pane();
        groupRoot.setStyle("-fx-background-color: #000000;");
        groupRoot.getChildren()
            .addAll(newGame, quit,mainTitle, createAudioControls());
        
        this.stage = stage;
        this.root = groupRoot;
    }
    
    private Button createAudioControls() {
        Button audioStatus = new Button();
        Image image = new Image(getClass().getResourceAsStream("/resources/images/audio_muted.png"));
        audioStatus.setGraphic(new ImageView(image));
        audioStatus.relocate(5, 500);
        audioStatus.setPadding(Insets.EMPTY);
        audioStatus.setStyle("-fx-background-color: transparent;");
        audioStatus.setOnAction((e) -> {
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
