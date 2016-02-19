package syndrome.ui;

import javafx.scene.Node;
import javafx.stage.Stage;
import syndrome.ui.impl.GameScreen;
import syndrome.ui.impl.MainMenu;

public class GUIManager {
    
    private SyndromeGUI mainMenu;
    private GameScreen gameScreen;
    private Stage stage;
    private Node background;
    
    public GUIManager() {
        this.mainMenu = new MainMenu();
        this.gameScreen = new GameScreen();
        
        Stage root = new Stage();
        root.setResizable(false);
        root.setTitle("Jarmo's Syndrome");
        
        this.stage = root;
    }
    
    public void drawMainMenu() {
        mainMenu.build(stage);
        mainMenu.show();
    }
    
    public void hideMainMenu() {
        mainMenu.hide();
    }
    
    public void drawGameScreen() {
        gameScreen.build(stage);
        gameScreen.show();
    }
    
    public GameScreen getGameScreen() {
        return gameScreen;
    }
    
    public void setBackground(Node node) {
        this.background = node;
    }
    
    public Node getBackground() {
        return background;
    }

}
