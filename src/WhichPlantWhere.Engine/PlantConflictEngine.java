package WhichPlantWhere.Engine;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlantConflictEngine {

    JSONArray conflicts;

    public PlantConflictEngine(JSONObject conflictsJson) {
       this.conflicts =  (JSONArray) conflictsJson.get("conflicts");
    }

    public Map check(ArrayList plants) {
        if (plants.size() > 1) {
            HashMap toReturn = new HashMap<String, String>();

            JSONObject firstConflictList = this.conflicts.getJSONObject(0);
            JSONArray thingsThatFirstConflictConflictsWith = firstConflictList.getJSONArray("conflictsWith");

            for(int i = 0; i < thingsThatFirstConflictConflictsWith.length(); ++i ) {
                if (plants.contains(thingsThatFirstConflictConflictsWith.get(i))) {
                    toReturn.put(firstConflictList.getString("veg"), thingsThatFirstConflictConflictsWith.get(i));
                }
            }
           return toReturn;
        }
        return new HashMap();
    }

    public String getNameOfPlantAt(int index) {
        JSONObject firstConflict = (JSONObject) this.conflicts.get(0);
        return (String) firstConflict.keys().next();
    }
}
