package WhichPlantWhere.Engine.Conflicts.Tests;

import WhichPlantWhere.Engine.Conflicts.Conflict;
import WhichPlantWhere.Engine.Conflicts.ConflictElementMapper;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConflictElementMapperTests {

    @Test
    public void whenAValidXmlFileIsLoadedArrayListOfPlantObjectsIsReturned() throws Exception {
        String veg = String.valueOf(Math.random());
        String imgUrl = String.valueOf(Math.random());
        Element element = this.createBasicConflictElement(veg, imgUrl);

        Conflict constructedPlant = (Conflict) new ConflictElementMapper().mapElement(element);

        assertEquals(veg, constructedPlant.getKey());
    }

    private Element createBasicConflictElement(String name, String imgUrl) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();

        Element element = doc.createElement("conflict");
        Element conflicts = doc.createElement("conflicts");
        conflicts.appendChild(this.createElementWithValue(doc, "conflict", "beetroot"));
        element.appendChild(conflicts);
        element.appendChild(this.createElementWithValue(doc, "veg", name));
        return element;
    }


    private Element createElementWithValue(Document doc, String tagName, String value) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}