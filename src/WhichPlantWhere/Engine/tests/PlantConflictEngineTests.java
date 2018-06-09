package WhichPlantWhere.Engine.tests;

import WhichPlantWhere.Engine.Conflict;
import WhichPlantWhere.Engine.PlantConflictEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlantConflictEngineTests {


    @Test
    public void whenThePlantConflictFinderIsPassedAnEmptyArrayListItReturnsAnEmptyList()  {
        ArrayList<Conflict> conflicts = new ArrayList();
        conflicts.add(new Conflict("carrot", new ArrayList<>(Arrays.asList("beetroot"))));

        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(conflicts);
        Map found  = plantConflictFinder.check(new ArrayList<>());
        assertTrue(found.isEmpty());
    }

    @Test
    public void whenThereIsNoConflictAnEmptyArrayIsReturned() {
        ArrayList<Conflict> conflicts = new ArrayList();
        conflicts.add(new Conflict("carrot", new ArrayList<>(Arrays.asList("beetroot"))));

        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(conflicts);
        Map found = plantConflictFinder.check(new ArrayList<>(Arrays.asList("carrot", "aubergine")));
        assertTrue(found.isEmpty());
    }

   @Test
    public void whenThereIsAConflictIsSpottedItReturnsTheConflict() {
       ArrayList<Conflict> conflicts = new ArrayList();
       conflicts.add(new Conflict("carrot", new ArrayList<>(Arrays.asList("beetroot"))));

        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(conflicts);
        Map found = plantConflictFinder.check(new ArrayList<>(Arrays.asList("carrot", "beetroot")));
        assertTrue(found.get("carrot").equals("beetroot"));
    }

    @Test
    public void whenThereAreMutipleElementsInTheArrayTheConflictEnginStillFindsTheConflict() {
        ArrayList<Conflict> conflicts = new ArrayList();
        conflicts.add(new Conflict("parsley", new ArrayList<>(Arrays.asList("celary"))));
        conflicts.add(new Conflict("carrot", new ArrayList<>(Arrays.asList("beetroot"))));

        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(conflicts);
        Map found = plantConflictFinder.check(new ArrayList<>(Arrays.asList("carrot", "beetroot")));
        assertTrue(found.get("carrot").equals("beetroot"));
    }

    @Test
    public void whenTheConflictIsRepeatedThereISOnlyOneErrorProduced() {
        ArrayList<Conflict> conflicts = new ArrayList();
        conflicts.add(new Conflict("carrot", new ArrayList<>(Arrays.asList("beetroot"))));

        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(conflicts);
        Map found = plantConflictFinder.check(new ArrayList<>(Arrays.asList("carrot", "carrot", "beetroot")));
        assertEquals(1, found.size());
    }

    @Test
    public void whenAPlantIsInTheConflictListButDoesNotConflictWithTheAssociatedVegThePlantConfigEngineReturnsNoResponse() {
        ArrayList<Conflict> conflicts = new ArrayList();
        conflicts.add(new Conflict("carrot", new ArrayList<>(Arrays.asList("beetroot"))));

        PlantConflictEngine plantConflictFinder = new PlantConflictEngine(conflicts);
        Map found = plantConflictFinder.check(new ArrayList<>(Arrays.asList("beetroot", "celary")));
        assertEquals(0, found.size());
    }


}