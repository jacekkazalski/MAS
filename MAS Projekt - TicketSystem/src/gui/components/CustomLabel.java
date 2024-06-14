package gui.components;

import gui.AppColors;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setForeground(AppColors.DARK_TEXT_COLOR);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    public CustomLabel(String text, int fontSize, boolean bold) {
        super(text);
        if (bold) setFont(new Font("Arial", Font.BOLD, fontSize));
        else setFont(new Font("Arial", Font.PLAIN, fontSize));
        setForeground(AppColors.DARK_TEXT_COLOR);
    }
    public CustomLabel(String text, int fontSize, boolean bold, int alignment) {
        super(text);
        if (bold) setFont(new Font("Arial", Font.BOLD, fontSize));
        else setFont(new Font("Arial", Font.PLAIN, fontSize));
        setForeground(AppColors.DARK_TEXT_COLOR);
        setHorizontalAlignment(alignment);
    }
}
