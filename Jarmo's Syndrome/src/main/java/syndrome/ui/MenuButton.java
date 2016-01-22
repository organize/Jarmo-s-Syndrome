package syndrome.ui;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author Axel Wallin
 */
public class MenuButton extends Button {
    
    public MenuButton(String text, String color) {
        super.setText(text);
        super.setTextFill(Paint.valueOf(color));
        super.setFont(Font.font("8BIT WONDER", 15));
        super.setMaxSize(500, 500);
        super.getStylesheets().add(getClass().getResource("/resources/css/general_button.css").toExternalForm());
    }
    
}
