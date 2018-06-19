package WhichPlantWhere.Engine.utils;

import WhichPlantWhere.Engine.Exceptions.LoaderException;
import WhichPlantWhere.Engine.Plants.Plant;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Loader {
    ElementMapper elementMapper;
    String elementToLoad;

    public Loader(ElementMapper elementMapper, String elementToLoad) {
        this.elementMapper = elementMapper;
        this.elementToLoad = elementToLoad;
    }
    public ArrayList loadFrom(String fileToLoadFrom ) throws LoaderException {
        ArrayList<Element> elements = ElementFinder.getElementFrom(fileToLoadFrom,  this.elementToLoad);
        ArrayList<Plant> plants = (ArrayList) elements.stream().map((element) -> this.elementMapper.mapElement(element)).collect(Collectors.toList());
        return plants;
    }
}
