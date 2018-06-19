package WhichPlantWhere.Engine.utils;

import WhichPlantWhere.Engine.Exceptions.LoaderException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class ElementFinder {
    public static ArrayList getElementFrom(String nameOFiileToLoadFrom, String elementName) throws LoaderException {
        try {

            Document document = getXMLObjectsFromFile(new File(nameOFiileToLoadFrom));
            return  getElementsWithTagName(document, elementName);
        } catch (Exception e) {
            throw new LoaderException();
        }
    }

    private static Document getXMLObjectsFromFile(File fileToLoadFrom) throws Exception {
        return DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(fileToLoadFrom);
    }

    public static ArrayList getElementsWithTagName(Document document, String tagName) {
        ArrayList<Element> elements = new ArrayList<>();
        for(int i = 0; i < document.getElementsByTagName(tagName).getLength(); ++i ) {
            elements.add((Element) document.getElementsByTagName(tagName).item(i));
        }
        return elements;
    }
}
