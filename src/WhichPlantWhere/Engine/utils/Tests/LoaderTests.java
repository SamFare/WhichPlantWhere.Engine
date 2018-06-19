package WhichPlantWhere.Engine.utils.Tests;

import WhichPlantWhere.Engine.Exceptions.LoaderException;
import WhichPlantWhere.Engine.Plants.Plant;
import WhichPlantWhere.Engine.utils.Loader;
import WhichPlantWhere.Engine.Plants.PlantElementMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class LoaderTests {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = LoaderException.class)
    public void thePlantLoaderThrowsAnExceptionWhenGivenAnIvalidResourceFile() throws LoaderException {
        new Loader(new PlantElementMapper(), "veg").loadFrom("");
    }

    @Test(expected = LoaderException.class)
    public void whenAValidXmlFileIsLoadedButTheResourceIsNotInnValidFormItThrows() throws LoaderException, IOException {
        File invalidXml = folder.newFile("invalidXml.xml");
        new Loader(new PlantElementMapper(), "veg").loadFrom(invalidXml.getAbsolutePath());
    }

    @Test
    public void whenAValidXmlWithMutipleVegIsParsedMutipleVegAreReturned() throws IOException, LoaderException {
        String fakeContennts =  "<vegetables><veg><name>test</name><imgUrl>test</imgUrl></veg><veg><name>test</name><imgUrl>test</imgUrl></veg></vegetables>";
        File invalidXml = createFakeFileWithContents(fakeContennts);
        ArrayList<Plant> plants = new Loader(new PlantElementMapper(), "veg").loadFrom(invalidXml.getAbsolutePath());

        assertEquals(2, plants.size());

    }

    @Test
    public void whenAValidXmlWitNoVegIsParsedNoVegAreReturned() throws IOException, LoaderException {
        String fakeContennts =  "<vegetables></vegetables>";
        File invalidXml = createFakeFileWithContents(fakeContennts);
        ArrayList<Plant> plants = new Loader(new PlantElementMapper(), "veg").loadFrom(invalidXml.getAbsolutePath());

        assertEquals(0, plants.size());

    }

    private File createFakeFileWithContents(String contents) throws IOException {
        File invalidXml = folder.newFile("invalidXml.xml");
        PrintWriter out = new PrintWriter(invalidXml.getAbsolutePath());
        FileUtils.writeStringToFile(invalidXml,contents, (Charset) null);
        return invalidXml;
    }
}
