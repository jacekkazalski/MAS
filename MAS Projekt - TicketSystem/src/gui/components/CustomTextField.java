package gui.components;

import gui.AppColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField implements FocusListener {
    private final String placeholder;
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
    // Sprawdza, czy wszystkie pola mają niedomyślne wartości
    public boolean isFilled() {
        return !getText().isBlank() && !getText().equals(placeholder);
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
        if (getText().isBlank()) {
            setText(placeholder);
            setForeground(Color.DARK_GRAY);
        }
    }

    @Override
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
}
