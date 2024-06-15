package gui.components;

import gui.AppColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField implements FocusListener {
    private String placeholder;
    public CustomTextField(String placeholder) {
        this.placeholder = placeholder;
        setText(placeholder);
        initialize();
    }
    private void initialize() {
        setBackground(AppColors.LIGHT_TEXT_COLOR);
        setForeground(Color.DARK_GRAY);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setSize(50,20);
        Border border = BorderFactory.createLineBorder(AppColors.ACCENT_COLOR);
        setBorder(border);
        addFocusListener(this);
    }
    public boolean isFilled() {
        if(getText().isEmpty() || getText().equals(placeholder)) {
            return false;
        }
        return true;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(placeholder)) {
            setText(" ");
            setForeground(AppColors.DARK_TEXT_COLOR);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setText(placeholder);
            setForeground(Color.DARK_GRAY);
        }
    }
}
