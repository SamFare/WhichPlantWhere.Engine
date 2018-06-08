package WhichPlantWhere.Engine;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlantConflictEngine {

    ArrayList<Conflict> conflicts;

    public PlantConflictEngine(ArrayList<Conflict> conflicts) {
       this.conflicts =  conflicts;
    }

    public Map check(ArrayList plants) {
       if (plants.size() > 1) {
            HashMap toReturn = new HashMap<String, String>();


           for(Conflict conflict : this.conflicts) {
               List<String> foundConflicts = this.getConflicts(conflict.values, plants);

               if(foundConflicts.size() > 0) {
                   for (String foundConflict : foundConflicts) {
                       toReturn.put(conflict.key, foundConflict);
                   }

               }
           }



           return toReturn;
        }
        return new HashMap();
    }

    private List getConflicts(ArrayList<String> potentialConflict, ArrayList<String> plants) {
        return potentialConflict
                .stream()
                .filter(conflictingElement -> plants.contains(conflictingElement) == true)
                .collect(Collectors.toList());
    }




}
