package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class DataModel implements Serializable {
    //TODO: Zapis ekstensji i odczyt z pliku
    private static Set<DataModel> extent = new HashSet<>();
    private final int objectId;

    public DataModel() {
        this.objectId = extent.stream().mapToInt(e -> e.objectId).max().orElse(0) + 1;
        extent.add(this);
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
