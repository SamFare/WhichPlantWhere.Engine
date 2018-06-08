package WhichPlantWhere.Engine.tests;

import WhichPlantWhere.Engine.PlantConflictEngine;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PlantConflictEngineTests {


    @Test
    public void whenThePlantConflictFinderIsPassedAnEmptyArrayListItReturnsAnEmptyList()  {
        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(new JSONObject("{\"conflicts\": [{\"veg\": \"carrot\", \"conflictsWith\" :  [\"beetroot\"]}]}"));
        Map found  = plantConflictFinder.check(new ArrayList<>());
        assertTrue(found.isEmpty());
    }

    @Test
    public void whenThereIsNoConflictAnEmptyArrayIsReturned() {
        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(new JSONObject("{\"conflicts\": [{\"veg\": \"carrot\", \"conflictsWith\" :  [\"beetroot\"]}]}"));
        Map found = plantConflictFinder.check(new ArrayList<>(Arrays.asList("carrot", "aubergine")));
        assertTrue(found.isEmpty());
    }

   @Test
    public void whenThereIsAConflictIsSpottedItReturnsTheConflict() {
        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(new JSONObject("{\"conflicts\": [{\"veg\": \"carrot\", \"conflictsWith\" :  [\"beetroot\"]}]}"));
        Map found = plantConflictFinder.check(new ArrayList<>(Arrays.asList("carrot", "beetroot")));
        assertTrue(found.get("carrot").equals("beetroot"));
    }
}