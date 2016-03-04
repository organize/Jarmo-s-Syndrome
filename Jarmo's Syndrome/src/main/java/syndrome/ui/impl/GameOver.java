package syndrome.ui.impl;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import syndrome.other.SyndromeFactory;
import syndrome.ui.MenuButton;
import syndrome.ui.SyndromeGUI;

/**
 * Game over screen, displayed at the end of a game.
 * 
 * @author Axel Wallin
 */
public class GameOver implements SyndromeGUI {
    
    private final List<Node> components;
    
    public GameOver() {
        this.components = new ArrayList<>();
    }

    @Override
    public void build(Stage stage) {
        Rectangle base = new Rectangle();
        base.setWidth(300);
        base.setHeight(300);
        base.setFill(Paint.valueOf(Color.BLACK.toString()));
        
        Text mainLabel = new Text("Game Over!");
        mainLabel.setFont(Font.font("8BIT WONDER", 16));
        mainLabel.setFill(Paint.valueOf("white"));
        mainLabel.setTranslateY(-100);
        
        Text hiScore = new Text("Hiscore: " 
                + SyndromeFactory.getWorld().getPlayer().getPoints());
        hiScore.setFont(Font.font("8BIT WONDER", 8));
        hiScore.setFill(Paint.valueOf("white"));
        hiScore.setTranslateY(-75);
        
        MenuButton menu = new MenuButton("Menu", "white");
        menu.setOnAction((e) -> {
            SyndromeFactory.getGUIManager().returnToMenu();
        });
        
        MenuButton exit = new MenuButton("Exit", "white");
        exit.setTranslateY(50);
        exit.setOnAction((e) -> {
            System.exit(0);
        });

        components.add(base);
        components.add(mainLabel);
        components.add(hiScore);
        components.add(exit);
        components.add(menu);
    }

    @Override
    public void show() {
        Pane gamePane = SyndromeFactory.getWorld().getGamePane();
        gamePane.getChildren().addAll(components);
    }

    @Override
    public void hide() {
    }

    @Override
    public void destroy() {
    }

}
