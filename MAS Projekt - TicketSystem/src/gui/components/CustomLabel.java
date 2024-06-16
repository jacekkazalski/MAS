package gui.components;

import gui.AppColors;
import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
        initialize();
    }

    public CustomLabel(String text, int fontSize, boolean bold, boolean centered) {
        super(text);
        initialize();
        if (bold) setFont(new Font("Arial", Font.BOLD, fontSize));
        if (centered) setHorizontalAlignment(SwingConstants.CENTER);
    }
    private void initialize(){
        setFont(new Font("Arial", Font.PLAIN, 16));
        setForeground(AppColors.DARK_TEXT_COLOR);
        setHorizontalAlignment(SwingConstants.LEFT);
    }
}
