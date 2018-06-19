package WhichPlantWhere.Engine.Plants;

import WhichPlantWhere.Engine.utils.ElementMapper;

import org.w3c.dom.Element;

public class PlantElementMapper implements ElementMapper {
    @Override
    public Object mapElement(Object elementToMap) {
        Element plantElement = (Element) elementToMap;
        return new Plant(
                plantElement.getElementsByTagName("name").item(0).getTextContent(),
                plantElement.getElementsByTagName("imgUrl").item(0).getTextContent());
    }
}
