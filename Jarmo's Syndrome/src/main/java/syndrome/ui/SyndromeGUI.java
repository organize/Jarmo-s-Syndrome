package syndrome.ui;

import javafx.stage.Stage;

/**
 * An abstract representation of a GUI,
 * wraps JavaFX elements inside.
 * 
 * @author Axel Wallin
 */
public interface SyndromeGUI {
    
    /**
     * Builds all the components related to this GUI
     * so it is ready to be displayed.
     * 
     * @param stage the stage instance supplied by JavaFX library on startup.
     */
    public void build(Stage stage);
    
    /**
     * Displays the GUI.
     */
    public void show();
    
    /**
     * Hides the GUI.
     */
    public void hide();
    
    /**
     * Destroys the GUI and all it's components.
     */
    public void destroy();

}
