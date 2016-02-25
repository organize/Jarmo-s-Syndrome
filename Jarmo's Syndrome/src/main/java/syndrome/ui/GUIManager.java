package syndrome.ui;

import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import syndrome.ui.impl.GameScreen;
import syndrome.ui.impl.MainMenu;

public class GUIManager {
    
    private SyndromeGUI mainMenu;
    private GameScreen gameScreen;
    private Stage stage;
    private Node background;
    private Text unlockLabel, hiscoreLabel, healthLabel;
    
    public GUIManager() {
        this.mainMenu = new MainMenu();
        this.gameScreen = new GameScreen();
        
        Stage root = new Stage();
        root.setResizable(false);
        root.setTitle("Jarmo's Syndrome");
        
        this.stage = root;
        this.hiscoreLabel = null;
        
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
    
    public void setUnlockLabel(Text text) {
        this.unlockLabel = text;
    }
    
    public void setHiscoreLabel(Text text) {
        System.out.println("set");
        this.hiscoreLabel = text;
    }
    
    public void setHealthLabel(Text text) {
        this.healthLabel = text;
    }
    
    public void setNextUnlock(String text) {
        unlockLabel.setText(text);
    }
    
    public void setHiscore(double hiscore) {
        hiscoreLabel.setText("Hiscore: " + getHiscoreString(hiscore));
    }
    
    private String getHiscoreString(double hiscore) {
        if(hiscore >= 1000 && hiscore < 1000000) {
            hiscore = hiscore / 1000;
            return hiscore + "K";
        }
        if(hiscore >= 1000000) {
            hiscore = hiscore / 1000000;
            return hiscore + "M";
        }
        return "" + hiscore;
    }
    
    public void updateHealth(int[] data) {
        healthLabel.setText(data[0] + "/" + data[1]);
    }

}
