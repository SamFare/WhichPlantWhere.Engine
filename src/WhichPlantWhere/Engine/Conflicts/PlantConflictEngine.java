package WhichPlantWhere.Engine.Conflicts;

import WhichPlantWhere.Engine.Conflicts.Conflict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlantConflictEngine {

    ArrayList<Conflict> conflictRules;

    public PlantConflictEngine(ArrayList<Conflict> conflicts) {
       this.conflictRules =  conflicts;
    }

    public Map check(ArrayList plants) {
       if (plants.size() > 1) {
           return this.applyRules(this.conflictRules, plants);
        }
        return new HashMap();
    }

    private List conflictRuleViolated(ArrayList<String> potentialConflict, ArrayList<String> plants) {
        return potentialConflict
                .stream()
                .filter(conflictingElement -> plants.contains(conflictingElement) == true)
                .collect(Collectors.toList());
    }

    private List<Conflict> getRelevetConflictRules(List<String> plants) {
        return this.conflictRules.stream()
                .filter(conflictRule -> plants.contains(conflictRule.key) == true)
                .collect(Collectors.toList());
    }

    private HashMap<String, String> applyRules(List<Conflict> conflictRules, ArrayList<String> plants) {
        HashMap toReturn = new HashMap<String, String>();

        for(Conflict conflictRule : this.getRelevetConflictRules(plants)) {
            List<String> foundConflicts = this.conflictRuleViolated(conflictRule.getValues(), plants);
            toReturn.putAll(this.getConflictMap(foundConflicts, conflictRule));
        }

        return toReturn;
    }

    private Map<String, String> getConflictMap(List<String> foundConflicts, Conflict conflict) {
        HashMap toReturn = new HashMap<String, String>();
        for (String foundConflict : foundConflicts) {
            toReturn.put(conflict.key, foundConflict);
        }
        return  toReturn;
    }
}
