package syndrome.ui.impl;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import syndrome.other.input.KeyboardInput;
import syndrome.other.SyndromeFactory;
import syndrome.other.input.MouseInput;
import syndrome.ui.SyndromeGUI;

public class GameScreen implements SyndromeGUI {
    
    private Stage stage;
    private Group group;

    @Override
    public void build(Stage stage) {
        Pane root = new Pane();
        root.setStyle("-fx-background-image: url(\"/resources/images/game_background.png\")");
        
        Scene scene = new Scene(root, 1024, 768);
        Group gameGroup = new Group();
        
        /* tempcode */
        Rectangle r = new Rectangle();
        r.setX(300);
        r.setY(300);
        r.setWidth(50);
        r.setHeight(50);
        
        SyndromeFactory.getWorld().setRTest(r);
        SyndromeFactory.getWorld().setGamePane(root);
        scene.addEventHandler(MouseEvent.ANY, new MouseInput());
        scene.addEventHandler(KeyEvent.ANY, new KeyboardInput());
        root.getChildren().add(r);
        root.getChildren().add(gameGroup);
        stage.setScene(scene);
        stage.setFullScreen(true);
        this.stage = stage;
        this.group = gameGroup;
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
