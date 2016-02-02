package syndrome.ui;

import javafx.stage.Stage;
import syndrome.ui.impl.GameScreen;
import syndrome.ui.impl.MainMenu;

/**
 *
 * @author Axel Wallin
 */
public class GUIManager {
    
    private SyndromeGUI mainMenu;
    private SyndromeGUI gameScreen;
    private Stage stage;
    
    public GUIManager() {
        this.mainMenu = new MainMenu();
        this.gameScreen = new GameScreen();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Jarmo's Syndrome");
        this.stage = stage;
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

}
