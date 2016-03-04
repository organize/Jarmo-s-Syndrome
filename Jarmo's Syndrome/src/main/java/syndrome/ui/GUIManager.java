package syndrome.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import syndrome.Syndrome;
import syndrome.entity.Player;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;
import syndrome.ui.impl.GameScreen;
import syndrome.ui.impl.MainMenu;

/**
 * The GUI manager class, handles various GUI related tasks.
 * 
 * @author Axel Wallin
 */
public class GUIManager {
    
    private final SyndromeGUI mainMenu;
    private final GameScreen gameScreen;
    private final Stage stage;
    private Node background;
    private Text unlockLabel, hiscoreLabel, 
            healthLabel, levelLabel;
    
    /**
     * Creates a new instance of this class plus
     * a MainMenu and a GameScreen instance inside it.
     */
    public GUIManager() {
        this.mainMenu = new MainMenu();
        this.gameScreen = new GameScreen();
        
        Stage root = new Stage();
        root.setResizable(false);
        root.setTitle("Jarmo's Syndrome");
        
        this.stage = root;
        this.hiscoreLabel = null;
    }
    
    /**
     * Draw and display the main menu.
     */
    public void drawMainMenu() {
        mainMenu.build(stage);
        mainMenu.show();
    }
    
    /**
     * Hides the main menu.
     */
    public void hideMainMenu() {
        mainMenu.hide();
    }
    
    /**
     * Draws and displays the main game screen.
     */
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
        this.hiscoreLabel = text;
    }
    
    public void setHealthLabel(Text text) {
        this.healthLabel = text;
    }
    
    /**
     * Sets the next unlock text.
     * 
     * @param text the unlock text.
     */
    public void setNextUnlock(String text) {
        unlockLabel.setText(text);
    }
    
    /**
     * Sets the hiscore to the specified number.
     * 
     * @param hiscore the current score.
     */
    public void setHiscore(double hiscore) {
        hiscoreLabel.setText("Hiscore: " + getHiscoreString(hiscore));
    }

    /**
     * Updates the health label with the specified health values.
     * 
     * @param data an int array containing the current and max health.
     */
    public void updateHealth(int[] data) {
        healthLabel.setText(data[0] + "/" + data[1]);
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
    
    /**
     * Updates the level label with new health values.
     * 
     * @param player the player.
     */
    public void updateLevelLabel(Player player) {
        Location location = player.getLocation();
        if(levelLabel == null) {
            levelLabel = new Text();
            levelLabel.setFont(Font.font("8BIT WONDER", 14));
            levelLabel.setFill(Paint.valueOf("white")); 
            SyndromeFactory.getWorld()
                .getGamePane().getChildren().add(levelLabel);
        }
        levelLabel.setText("" + player.getLevel());
        levelLabel.setTranslateX(location.getX());
        levelLabel.setTranslateY(location.getY());
    }
    
    /**
     * Destroys the current game instance and returns
     * to the main menu.
     */
    public void returnToMenu() {
        gameScreen.destroy();
        SyndromeFactory.resetAll();
        Syndrome syndrome = new Syndrome();
        syndrome.start(null);
    }
}
