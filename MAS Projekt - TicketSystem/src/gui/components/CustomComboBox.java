package gui.components;

import gui.AppColors;

import javax.swing.*;
import java.awt.*;

public class CustomComboBox<T> extends JComboBox<T> {
    public CustomComboBox(T[] items) {
        super(items);
        setBackground(AppColors.LIGHT_TEXT_COLOR);
        setForeground(AppColors.DARK_TEXT_COLOR);
        setRenderer(new CustomComboBoxRenderer());
        setSize(50, 10);
    }

    private static class CustomComboBoxRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setBackground(isSelected ? AppColors.ACCENT_COLOR : AppColors.LIGHT_TEXT_COLOR);
            setForeground(isSelected ? AppColors.LIGHT_TEXT_COLOR : AppColors.DARK_TEXT_COLOR);
            return this;
        }
    }
}
