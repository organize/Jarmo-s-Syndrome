package syndrome.ui.impl;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import syndrome.entity.NPC;
import syndrome.entity.Player;
import syndrome.logic.map.Axis;
import syndrome.logic.projectile.Projectile;
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
        Scene scene = new Scene(root, 1024, 768);
        
        /* temporary code */
        Rectangle r = new Rectangle();
        r.setTranslateX(0);
        r.setTranslateY(0);
        r.setWidth(50);
        r.setHeight(50);
        
        SyndromeFactory.getWorld().setRTest(r);
        SyndromeFactory.getWorld().setGamePane(root);
        SyndromeFactory.getGUIManager().setBackground(background);
        
        root.getChildren().add(background);
        root.getChildren().add(r);
        root.getChildren().add(createInfobox());
        
        scene.addEventHandler(MouseEvent.ANY, new MouseInput());
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
        
    }

    @Override
    public void destroy() {
       
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
        infobox.setWidth(80);
        infobox.setHeight(60);
        infobox.setTranslateX(270);
        infobox.setTranslateY(-180);
        
        Text mainTitle = new Text("-Weapon-");
        mainTitle.setFont(Font.font ("8BIT WONDER", 8));
        mainTitle.setFill(Paint.valueOf("white")); 
        mainTitle.setTranslateX(280);
        mainTitle.setTranslateY(-170);
        
        Rectangle healthBar = new Rectangle();
        healthBar.setWidth(70);
        healthBar.setHeight(10);
        healthBar.setFill(Paint.valueOf("green"));
        
        healthBar.setTranslateX(275);
        healthBar.setTranslateY(-140);
        
        group.getChildren().add(infobox);
        group.getChildren().add(mainTitle);
        group.getChildren().add(healthBar);
        
        group.setTranslateX(270);
        group.setTranslateY(-190);
        return group;
    }

    public void handleScreenPan(Player player) {
        List<Projectile> projectiles = SyndromeFactory.getWorld().getProjectiles();
        List<NPC> activeNPCs = SyndromeFactory.getWorld().getNPCs();
        Node background = SyndromeFactory.getGUIManager().getBackground();
        Axis toUpdate = Axis.NONE;
        int absX = (int) Math.abs(player.getLocation().getX());
        int absY = (int) Math.abs(player.getLocation().getY());
        if (absX <= 180 && absY > 140) {
            toUpdate = Axis.X_AXIS;
        }
        if (absY <= 140 && absX > 180) {
            toUpdate = Axis.Y_AXIS;
        }
        if (absX <= 180 && absY <= 140) {
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
        final Axis lambdaAxis = toUpdate;
        if(lambdaAxis != Axis.NONE) {
            projectiles.forEach((Projectile proj) -> proj.updateTranslation(lambdaAxis));
            activeNPCs.forEach((NPC npc) -> npc.updateTranslation(lambdaAxis));
        }
    }
}
