package WhichPlantWhere.Engine.Plants.Tests;

import WhichPlantWhere.Engine.Plants.Plant;
import WhichPlantWhere.Engine.Plants.PlantElementMapper;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlantElementMapperTests {

    @Test
    public void whenAValidXmlFileIsLoadedArrayListOfPlantObjectsIsReturned() throws Exception {

        String name = String.valueOf(Math.random());
        String imgUrl = String.valueOf(Math.random());

        Document doc = DocumentBuilderFactory.newInstance()
                                            .newDocumentBuilder()
                                            .newDocument();

        Element element = doc.createElement("veg");

        Attr attrName = doc.createElement("name");
        attrName.setValue(name);

        Attr attrImgUrl = doc.createElement("imgUrl");
        attrImgUrl.setValue("imgUrl");

        element.setAttributeNode(attrName);
        element.setAttributeNode(attrImgUrl);

        Plant constructedPlant = (Plant) new PlantElementMapper().mapElement(element);



        assertEquals(name, constructedPlant.getName());
        assertEquals(imgUrl, constructedPlant.getImgUrl());
    }
}
