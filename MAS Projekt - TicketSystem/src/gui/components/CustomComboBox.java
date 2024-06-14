package gui.components;

import javax.swing.*;

public class CustomComboBox<T> extends JComboBox<T> {
    public CustomComboBox(T[] items) {
        super(items);
    }
}
