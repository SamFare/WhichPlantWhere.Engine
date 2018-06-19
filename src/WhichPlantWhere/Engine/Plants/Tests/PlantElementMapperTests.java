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

        Element element = this.createBasicVegElement(name, imgUrl);

        Plant constructedPlant = (Plant) new PlantElementMapper().mapElement(element);



        assertEquals(name, constructedPlant.getName());
        assertEquals(imgUrl, constructedPlant.getImgUrl());
    }

    private Element createBasicVegElement(String name, String imgUrl) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();

        Element element = doc.createElement("plant");
        element.appendChild(this.createElementWithValue(doc, "name", name));
        element.appendChild(this.createElementWithValue(doc, "imgUrl", imgUrl));
        return element;
    }


    private Element createElementWithValue(Document doc, String tagName, String value) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}
