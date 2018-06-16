package WhichPlantWhere.Engine.PlantLoader.Tests;

import WhichPlantWhere.Engine.Exceptions.PlantLoaderException;
import WhichPlantWhere.Engine.PlantLoader.Plant;
import WhichPlantWhere.Engine.PlantLoader.PlantLoader;
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

public class PlantLoaderTests {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = PlantLoaderException.class)
    public void thePlantLoaderThrowsAnExceptionWhenGivenAnIvalidResourceFile() throws PlantLoaderException{
        PlantLoader.loadFrom("");
    }

    @Test(expected = PlantLoaderException.class)
    public void whenAValidXmlFileIsLoadedButTheResourceIsNotInnValidFormItThrows() throws  PlantLoaderException, IOException {
        File invalidXml = folder.newFile("invalidXml.xml");
        PlantLoader.loadFrom(invalidXml.getAbsolutePath());
    }

    @Test
    public void whenAValidXmlFileIsLoadedArrayListOfPlantObjectsIsReturned() throws  PlantLoaderException, IOException {

        String name = String.valueOf(Math.random());
        String imgUrl = String.valueOf(Math.random());

        String fakeContennts =
                String.format(
                        "<vegetables><veg><name>%s</name><imgUrl>%s</imgUrl></veg></vegetables>",
                        name,
                        imgUrl);
        File invalidXml = createFakeFileWithContents(fakeContennts);

        ArrayList<Plant> plants = PlantLoader.loadFrom(invalidXml.getAbsolutePath());

        assertEquals(name, plants.get(0).getName());
        assertEquals(imgUrl, plants.get(0).getImgUrl());
    }

    @Test
    public void whenAValidXmlWithMutipleVegIsParsedMutipleVegAreReturned() throws IOException, PlantLoaderException{
        String fakeContennts =  "<vegetables><veg><name>test</name><imgUrl>test</imgUrl></veg><veg><name>test</name><imgUrl>test</imgUrl></veg></vegetables>";
        File invalidXml = createFakeFileWithContents(fakeContennts);
        ArrayList<Plant> plants = PlantLoader.loadFrom(invalidXml.getAbsolutePath());

        assertEquals(2, plants.size());

    }

    @Test
    public void whenAValidXmlWitNoVegIsParsedNoVegAreReturned() throws IOException, PlantLoaderException{
        String fakeContennts =  "<vegetables></vegetables>";
        File invalidXml = createFakeFileWithContents(fakeContennts);
        ArrayList<Plant> plants = PlantLoader.loadFrom(invalidXml.getAbsolutePath());

        assertEquals(0, plants.size());

    }

    private File createFakeFileWithContents(String contents) throws IOException {
        File invalidXml = folder.newFile("invalidXml.xml");
        PrintWriter out = new PrintWriter(invalidXml.getAbsolutePath());
        FileUtils.writeStringToFile(invalidXml,contents, (Charset) null);
        return invalidXml;
    }
}
