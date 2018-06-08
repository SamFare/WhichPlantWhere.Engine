package WhichPlantWhere.Engine;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlantConflictEngine {

    ArrayList<Conflict> conflicts;

    public PlantConflictEngine(ArrayList<Conflict> conflicts) {
       this.conflicts =  conflicts;
    }

    public Map check(ArrayList plants) {
       if (plants.size() > 1) {
            HashMap toReturn = new HashMap<String, String>();

            Conflict firstConflictList = this.conflicts.get(0) ;


            for(String conflict : firstConflictList.values) {
                if (plants.contains(conflict)) {
                    toReturn.put(firstConflictList.key, conflict);
                }
            }
           return toReturn;
        }
        return new HashMap();
    }
}
