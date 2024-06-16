package gui;

import model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class CustomView extends JPanel {
    protected final GridBagConstraints gbc;
    protected Set<DataModel> data = new HashSet<>();
    private int dataId;

    public CustomView() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
    }
    public void adjustPreferredSize() {
        Dimension preferredSize = new Dimension();
        for (Component c : getComponents()) {
            Rectangle bounds = c.getBounds();
            preferredSize.width = Math.max(preferredSize.width, bounds.x + bounds.width);
            preferredSize.height = Math.max(preferredSize.height, bounds.y + bounds.height);
        }
        setPreferredSize(preferredSize);
        revalidate();
        repaint();
    }
    // Odświeżenie widoku
    public void reloadView(Set<DataModel> data) {
        loadData(data);
        drawComponents();
        adjustPreferredSize();
    }
    // Wczytanie danych do widoku
    private void loadData(Set<DataModel> data) {
        this.data = data;
    }
    // Rysowanie komponentów dla danego widoku
    protected abstract void drawComponents();

    public Set<DataModel> getData() {
        return data;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
}
