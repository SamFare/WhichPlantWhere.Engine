package WhichPlantWhere.Engine;

import java.util.ArrayList;

public class Conflict {
    String key;
    ArrayList<String> values;

    public Conflict(String key, ArrayList<String> values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public ArrayList<String> getValues() {
        return values;
    }
}
