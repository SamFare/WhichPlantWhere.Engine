package WhichPlantWhere.Engine.Conflicts.Tests;

import WhichPlantWhere.Engine.Conflicts.Conflict;
import WhichPlantWhere.Engine.Conflicts.ConflictBuilder;
import WhichPlantWhere.Engine.Exceptions.BuildException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ConflictBuilderTests {

    @Test(expected = BuildException.class)
    public void whenThereIsNoJsonTheConflictBuilderThrowsANException() throws BuildException{
        ConflictBuilder.build("");
    }

    @Test
    public void whenThereIsValidJsonWithASingleElementThereIsAListContainingOneItemReturned() throws BuildException {
        List<Conflict> conflictList = ConflictBuilder.build("{'conflicts' : [{'veg' : 'carrot', 'conflictsWith': ['beetroot']}]}");
        Assert.assertEquals(conflictList.get(0).getKey(), "carrot" );
        Assert.assertTrue(conflictList.get(0).getValues().contains("beetroot"));
    }

    @Test
    public void whenThereIsValidJsonWithAMutipleElemenstThereIsAListContainingMoreThanOneItemReturned() throws BuildException {
        List<Conflict> conflictList = ConflictBuilder.build("{'conflicts' : [{'veg' : 'carrot', 'conflictsWith': ['beetroot']}, {'veg' : 'carrot', 'conflictsWith': ['beetroot']}]}");
        Assert.assertEquals(2, conflictList.size());
    }

    @Test(expected = BuildException.class)
    public void WhennTheJsonExistsButIsInvalidABuildExceptionIsThrown() throws BuildException {
        ConflictBuilder.build("This is invalid");
    }
}
