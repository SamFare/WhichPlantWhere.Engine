package WhichPlantWhere.Engine.Plants;

import WhichPlantWhere.Engine.Exceptions.LoaderException;
import WhichPlantWhere.Engine.utils.ElementFinder;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlantLoader {
    public ArrayList loadFrom(String fileToLoadFrom ) throws LoaderException {
        ArrayList<Element> elements = ElementFinder.getElementFrom(fileToLoadFrom, "veg");
        ArrayList<Plant> plants = (ArrayList) elements.stream().map((element) -> buildPlantFromXmlElement(element)).collect(Collectors.toList());
        return plants;
    }

    private static Plant buildPlantFromXmlElement(Element plantElemennt) {
       return  new Plant(
               plantElemennt.getElementsByTagName("name").item(0).getTextContent(),
               plantElemennt.getElementsByTagName("imgUrl").item(0).getTextContent());
    }
}
