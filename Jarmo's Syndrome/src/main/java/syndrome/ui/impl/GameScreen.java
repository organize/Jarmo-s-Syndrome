package syndrome.ui.impl;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.logic.map.Axis;
import syndrome.projectile.Projectile;
import syndrome.other.input.KeyboardInput;
import syndrome.other.SyndromeFactory;
import syndrome.other.input.MouseInput;
import syndrome.ui.SyndromeGUI;

public class GameScreen implements SyndromeGUI {
    
    private Stage stage;

    @Override
    public void build(Stage stage) {
        StackPane root = new StackPane();
        Node background = buildBackground();
        Scene scene = new Scene(root, 640, 480);
        
        Rectangle playerModel = createPlayerModel();
        
        SyndromeFactory.getWorld().setPlayerModel(playerModel);
        SyndromeFactory.getWorld().setGamePane(root);
        SyndromeFactory.getGUIManager().setBackground(background);
        
        root.getChildren().addAll(background, playerModel,
            createInfobox(), createHiscoreCounter(),
            createHealthBox());
        
        scene.addEventFilter(MouseEvent.ANY, new MouseInput());
        scene.addEventHandler(KeyEvent.ANY, new KeyboardInput());
        
        stage.setScene(scene);
        stage.setMaxHeight(480);
        stage.setMaxWidth(640);
        
        this.stage = stage;
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void destroy() {
       stage.close();
    }
    
    private Node buildBackground() {
        Rectangle background = new Rectangle();
        background.relocate(0, 0);
        background.setHeight(768);
        background.setWidth(1024);
        background.setFill(new ImagePattern(
                new Image("resources/images/game_background.png")));
        return background;
    }
    
    private Group createInfobox() {
        Group group = new Group();
        Rectangle infobox = new Rectangle();
        infobox.setWidth(85);
        infobox.setHeight(60);
        infobox.setTranslateX(270);
        infobox.setTranslateY(-180);
        
        Text mainTitle = new Text("NEXT UNLOCK");
        mainTitle.setFont(Font.font ("8BIT WONDER", 7));
        mainTitle.setFill(Paint.valueOf("white")); 
        mainTitle.setTranslateX(275);
        mainTitle.setTranslateY(-170);
        
        Text unlockLabel = new Text("triple burst");
        unlockLabel.setFont(Font.font ("8BIT WONDER", 7));
        unlockLabel.setFill(Paint.valueOf("red")); 
        unlockLabel.setTranslateX(275);
        unlockLabel.setTranslateY(-150);
        
        Text levelAt = new Text("At level 5");
        levelAt.setFont(Font.font ("8BIT WONDER", 8));
        levelAt.setFill(Paint.valueOf("white")); 
        levelAt.setTranslateX(275);
        levelAt.setTranslateY(-130);
        
        group.getChildren().addAll(infobox, mainTitle, 
                unlockLabel, levelAt);
        
        group.setTranslateX(270);
        group.setTranslateY(-190);
        
        SyndromeFactory.getGUIManager().setUnlockLabel(unlockLabel);
        return group;
    }
    
    private Group createHiscoreCounter() {
        Group group = new Group();
        Rectangle infobox = new Rectangle();
        infobox.setWidth(100);
        infobox.setHeight(15);
        infobox.setTranslateX(-260);
        infobox.setTranslateY(-210);
        
        Text hiscore = new Text("Hiscore: 0");
        hiscore.setFont(Font.font ("8BIT WONDER", 7));
        hiscore.setFill(Paint.valueOf("white")); 
        hiscore.setTranslateX(-250);
        hiscore.setTranslateY(-200);
        
        group.getChildren().addAll(infobox, hiscore);
        group.setTranslateX(-260);
        group.setTranslateY(-210);
        
        SyndromeFactory.getGUIManager().setHiscoreLabel(hiscore);
        return group;
    }
    
    private Group createHealthBox() {
        Group group = new Group();
        
        Rectangle body = new Rectangle();
        body.setWidth(110);
        body.setHeight(20);
        body.setTranslateX(0);
        body.setTranslateY(210);
        
        Text healthLabel = new Text("Health");
        healthLabel.setFont(Font.font("8BIT WONDER", 8));
        healthLabel.setFill(Paint.valueOf(Color.GOLD.toString())); 
        healthLabel.setTranslateX(5);
        healthLabel.setTranslateY(222);
        
        Text healthIndicator = new Text("100/100");
        healthIndicator.setFont(Font.font("8BIT WONDER", 8));
        healthIndicator.setFill(Paint.valueOf(Color.GOLD.toString())); 
        healthIndicator.setTranslateX(55);
        healthIndicator.setTranslateY(222);
        
        group.getChildren().addAll(body, healthLabel, healthIndicator);
        group.setTranslateX(0);
        group.setTranslateY(210);
        
        SyndromeFactory.getGUIManager().setHealthLabel(healthIndicator);
        return group;
    }

    /**
     * Calculate and draw the screen panning effect,
     * relative to the player's current movement direction
     * and updates the relative translation coordinates of 
     * every NPC and Projectile instance in the game world.
     * 
     * @param player the player of the game.
     */
    public void handleScreenPan(Player player) {
        List<Projectile> projectiles = SyndromeFactory.getWorld().getProjectiles();
        List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
        Node background = SyndromeFactory.getGUIManager().getBackground();
        final Axis lambdaAxis = handleBackgroundTranslation(background, player);
        if(lambdaAxis != Axis.NONE) {
            projectiles.forEach((Projectile proj) -> proj.updateTranslation(lambdaAxis));
            activeNPCs.forEach((NPC npc) -> npc.updateTranslation(lambdaAxis));
        }
    }
    
    private Axis handleBackgroundTranslation(Node background, Player player) {
        Axis toUpdate = Axis.NONE;
        int absX = (int) Math.abs(player.getLocation().getX());
        int absY = (int) Math.abs(player.getLocation().getY());
        if(absX <= 180 && absY > 140) {
            toUpdate = Axis.X_AXIS;
        }
        if(absY <= 140 && absX > 180) {
            toUpdate = Axis.Y_AXIS;
        }
        if(absX <= 180 && absY <= 140) {
            toUpdate = Axis.X_AND_Y_AXIS;
        }
        switch (toUpdate) {
            case X_AXIS:
                background.setTranslateX(-player.getLocation().getX());
                break;
            case Y_AXIS:
                background.setTranslateY(-player.getLocation().getY());
                break;
            case X_AND_Y_AXIS:
                background.setTranslateX(-player.getLocation().getX());
                background.setTranslateY(-player.getLocation().getY());
                break;
        }
        return toUpdate;
    }
    
    private Rectangle createPlayerModel() {
        return new Rectangle(0, 0, 50, 50);
    }
}
