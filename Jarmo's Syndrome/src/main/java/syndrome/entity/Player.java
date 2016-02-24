package syndrome.entity;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.other.SyndromeFactory;
import syndrome.other.Toolbox;

/**
 * A class that represents the player character.
 * 
 * @see {@Entity} for documentation.
 * @author Axel Wallin
 */
public class Player extends Entity {
    
    private Direction direction;
    private double rotation;
    
    private int points, level;
    private Location lastMousePosition;
    
    private Text levelLabel;
    
    /**
     * Creates a new player instance at the default location
     * of [0, 0].
     */
    public Player() {
        super(new Location(0, 0));
        this.rotation = 0.0D;
        this.direction = Direction.NONE;
        this.points = 0;
        this.level = 1;
        this.lastMousePosition = new Location(0, 0);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(double x, double y) {
        location.transform(x, y);
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    @Override
    public void tick(long now) {
        if(!direction.equals(Direction.NONE)) {
            handleMovement();
            updateRotation(lastMousePosition);
        }
        updateLevelLabel();
    }

    @Override
    public void setRotation(double degrees) {
        this.rotation = degrees;
    }

    @Override
    public double getRotation() {
        return rotation;
    }
    
    @Override
    public Location[] getBounds() {
        Location[] bounds = new Location[4];
        bounds[0] = location;
        bounds[1] = new Location(location.getX() + 8, 
                location.getY());
        bounds[2] = new Location(location.getX(), 
                location.getY() + 8);
        bounds[3] = new Location(location.getX() + 8, 
                location.getY() + 8);
        return bounds;
    }
    
    @Override
    public boolean collidesWith(Entity other) {
        java.awt.Rectangle bounds = new java.awt.Rectangle(
                (int) other.getBounds()[0].getX(),
                (int) other.getBounds()[0].getY(),
                other.getSize(), other.getSize());
        
        java.awt.Rectangle ownBounds = new java.awt.Rectangle(
                (int) this.getBounds()[0].getX(),
                (int) this.getBounds()[0].getY(),
                this.getSize(), this.getSize());
        return ownBounds.contains(bounds);
    }
    
    @Override
    public int getSize() {
        return 8;
    }
    
    public void addPoints(int points) {
        this.points += points;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    /**
     * Updates the player rotation based on the supplied location.
     * 
     * @param towards the location to face towards.
     */
    public void updateRotation(Location towards) {
        double angleDegrees = SyndromeFactory.getToolbox().calculateRotation(towards);
        setRotation(angleDegrees);
        this.lastMousePosition = towards;
    }
            
    private void handleMovement() {
        Toolbox toolbox = SyndromeFactory.getToolbox();
        int[] delta = toolbox.directionToDelta(direction);
        setLocation(location.getX() + delta[0], location.getY() + delta[1]);
    }

    @Override
    public void destroy() {

    }
    
    public int getLevel() {
        return level;
    }
    
    public Location getLastMousePosition() {
        return lastMousePosition;
    }

    public Direction getDirection() {
        return direction;
    }
    
    private void updateLevelLabel() {
        if(levelLabel == null) {
            levelLabel = new Text();
            levelLabel.setFont(Font.font("8BIT WONDER", 14));
            levelLabel.setFill(Paint.valueOf("white")); 
            SyndromeFactory.getWorld()
                .getGamePane().getChildren().add(levelLabel);
        }
        if(points >= (level * level) * 100) {
            level += 1;
        }
        levelLabel.setText("" + level);
        levelLabel.setTranslateX(location.getX());
        levelLabel.setTranslateY(location.getY());
    }
}
