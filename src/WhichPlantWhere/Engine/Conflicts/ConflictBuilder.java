package WhichPlantWhere.Engine.Conflicts;

import WhichPlantWhere.Engine.Conflicts.Conflict;
import WhichPlantWhere.Engine.Exceptions.BuildException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConflictBuilder {
    public static List<Conflict> build(String jsonConflicts) throws BuildException {
        if (!jsonConflicts.isEmpty()) {
            try {

                JSONArray conflictsArray = new JSONObject(jsonConflicts).getJSONArray("conflicts");

                List<Conflict> returnList = new ArrayList<>();

                for (int i = 0; i < conflictsArray.length(); ++i) {
                    returnList.add(buildConflict(conflictsArray));
                }

                return returnList;
            } catch(Exception e) {
                throw new BuildException();
            }

        }
        throw new BuildException();
    }

    private static Conflict buildConflict(JSONArray conflictsArray) {
        JSONObject conflictForConversion = conflictsArray.getJSONObject(0);
        JSONArray values = conflictForConversion.getJSONArray("conflictsWith");
        return new Conflict(conflictForConversion.get("veg").toString(), convertJsonArrayToArrayList(values));
    }

    private static ArrayList convertJsonArrayToArrayList(JSONArray values) {
        ArrayList<String> valuesAsList = new ArrayList<>();

        for(int i =0; i < values.length(); i++) {
            valuesAsList.add(values.getString(i));
        }

        return valuesAsList;
    }
}
