package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class DataModel implements Serializable {
    private static Set<DataModel> extent = new HashSet<>();
    private final int objectId;

    public DataModel() {
        this.objectId = extent.stream().mapToInt(e -> e.objectId).max().orElse(0) + 1;
        extent.add(this);
    }
    public static void writeExtent(ObjectOutputStream oos) throws IOException {
        oos.writeObject(extent);
    }
    public static void readExtent(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        extent = (Set<DataModel>) ois.readObject();
    }
    public static int getExtentSize() {
        return extent.size();
    }

    public static Set<DataModel> getExtent() {
        return extent;
    }

    public static void setExtent(Set<DataModel> extent) {
        DataModel.extent = extent;
    }

    public int getObjectId() {
        return objectId;
    }

    public abstract String getInfo();
}
