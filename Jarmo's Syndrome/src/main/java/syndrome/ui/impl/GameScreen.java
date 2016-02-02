package syndrome.ui.impl;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import syndrome.logic.map.Location;
import syndrome.other.KeyboardInput;
import syndrome.other.SyndromeFactory;
import syndrome.ui.SyndromeGUI;

/**
 *
 * @author Axel Wallin
 */
public class GameScreen implements SyndromeGUI {
    
    private Stage stage;
    private Group group;

    @Override
    public void build(Stage stage) {
        Pane root = new Pane();
        root.setStyle("-fx-background-image: url(\"/resources/images/game_background.png\")");
        
        Scene scene = new Scene(root, 1024, 768);
        Group gameGroup = new Group();
        root.setCursor(Cursor.NONE);
        Rectangle r = new Rectangle();
        r.setX(300);
        r.setY(300);
        r.setWidth(50);
        r.setHeight(50);
        SyndromeFactory.getWorld().setRTest(r);
        scene.setOnMouseMoved((MouseEvent event) -> {
            Location location = new Location(300, 300);
            Location other = new Location(600, 600);
            Location midpoint = location.midpoint(other);
            double angleRadians= Math.atan2(event.getY() - midpoint.getY(), event.getX() - midpoint.getX());
 
            double angleDegrees = angleRadians * (180 / Math.PI) + 90; 
            if(angleDegrees < 0) {
                angleDegrees+=360;
            }
            r.setRotate(angleDegrees);
        });
        KeyboardInput inputListener = new KeyboardInput();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, inputListener);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, inputListener);
        root.getChildren().add(r);
        root.getChildren().add(gameGroup);
        stage.setScene(scene);
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
    
    private Pane getRoot() {
        return (Pane) stage.getScene().getRoot();
    }

}
