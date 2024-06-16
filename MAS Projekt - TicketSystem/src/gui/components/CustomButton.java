package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text, Color background, Color foreground) {
        super(text);
        setBackground(background);
        setForeground(foreground);
        initialize();

    }
    private void initialize() {
        setFont(new Font("Arial", Font.PLAIN, 16));
    }
}
