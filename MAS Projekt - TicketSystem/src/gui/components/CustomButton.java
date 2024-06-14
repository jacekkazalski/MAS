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
        setFocusPainted(false);
    }
    public void setCustomBackground(Color background) {
        setBackground(background);
    }
    public void setCustomForeground(Color foreground) {
        setForeground(foreground);
    }
}
