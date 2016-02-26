package syndrome.entity;

import syndrome.logic.map.Direction;
import syndrome.logic.map.Location;
import syndrome.projectile.Projectile;
import syndrome.logic.projectile.impl.Halt;
import syndrome.other.SyndromeFactory;
import syndrome.other.Toolbox;
import syndrome.ui.GUIManager;

/**
 * A class that represents the player character.
 * 
 * @see {@Entity} for documentation.
 * @author Axel Wallin
 */
public class Player extends Entity {

    private double rotation, speed;
    private int points, level;
    private int[] healthData;
    
    private Direction direction;
    private Location lastMousePosition;
    
    /**
     * Creates a new player instance at the default location
     * of [0, 0] and sets the default values of
     * (rot=0, dir=NONE, points=0, level=1, speed=1).
     */
    public Player() {
        super(new Location(0, 0));
        this.rotation = 0.0D;
        this.direction = Direction.NONE;
        this.points = 0;
        this.level = 1;
        this.speed = 1;
        this.lastMousePosition = new Location(0, 0);
        this.healthData = new int[]{100, 100};
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
        if(points != 0) {
            updateLevelLabel();
            SyndromeFactory.getGUIManager().updateHealth(healthData);
        }
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
    public int getSize() {
        return 8;
    }
    
    @Override
    public void destroy() {
        SyndromeFactory.getSettings().togglePause();
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
    
    /**
     * Refreshes the player's speed and HP value,
     * which is incremented by an 8th of the current max HP.
     * This method should only be called from {@SyndromeTimer}.
     */
    public void refresh() {
        speed = 1.0;
        healthData[0] = healthData[0] + (healthData[1] / 8);
        if(healthData[0] > healthData[1]) {
            healthData[0] = healthData[1];
        }
    }
    
    /**
     * Handle collision with a projectile.
     * @param proj the projectile.
     */
    public void handleCollision(Projectile proj) {
        if(proj instanceof Halt) {
            speed = 0.4;
        }
    }

    /**
     * Inflict damage to the player.
     * @param amount the amount of damage to inflict.
     */
    public void inflictDamage(double amount) {
        healthData[0] -= amount;
        if(healthData[0] <= 0) {
            this.destroy();
        }
    }
    
    /** 
     * Increments the player's points by a defined amount.
     * @param points the amount of points we want to increment.
     */
    public void addPoints(int points) {
        this.points += points;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
        
    public int getLevel() {
        return level;
    }
    
    public double getSpeed() {
        return speed;
    }
      
    public Location getLastMousePosition() {
        return lastMousePosition;
    }

    public Direction getDirection() {
        return direction;
    }
   
    private void checkUnlocks() {
        GUIManager guiManager = SyndromeFactory.getGUIManager();
        if(level >= 5) {
            guiManager.setNextUnlock(" -todo-");
        }
        guiManager.setHiscore(points);
    }
     
    private void updateLevelLabel() {
        if(points >= (level * level) * 100) {
            level += 1;
            if(healthData[1] < 500) {
                healthData[1] = healthData[1] + (level * 20);
            }
        }
        SyndromeFactory.getGUIManager().updateLevelLabel(this);
        checkUnlocks();
    }
                
    private void handleMovement() {
        Toolbox toolbox = SyndromeFactory.getToolbox();
        double[] delta = toolbox.directionToDelta(direction);
        setLocation(location.getX() + delta[0], location.getY() + delta[1]);
    }
}