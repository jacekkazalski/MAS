package model;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class DataModel implements Serializable {
    private final int objectId;
    private static Set<DataModel> extent = new HashSet<>();
    private final SwingPropertyChangeSupport propChangeFirer = new SwingPropertyChangeSupport(this);

    public DataModel() {
        this.objectId = extent.stream().mapToInt(e -> e.objectId).max().orElse(0) + 1;
        extent.add(this);
    }
    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
    }

    public int getObjectId() {
        return objectId;
    }

    public static Set<DataModel> getExtent() {
        return extent;
    }

    public static void setExtent(Set<DataModel> extent) {
        DataModel.extent = extent;
    }
    public abstract String getInfo();
}
