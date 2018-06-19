package WhichPlantWhere.Engine.Conflicts.Tests;

import WhichPlantWhere.Engine.Exceptions.LoaderException;
import WhichPlantWhere.Engine.Conflicts.ConflictLoader;
import org.junit.Test;

public class ConflictLoaderTests {

    @Test(expected = LoaderException.class)
    public void whenTheConflictLoaderThrowsAnExceptionWhenGivenAnIvalidResourceFile() throws LoaderException {
        new ConflictLoader().loadFrom("");
    }
}