package WhichPlantWhere.Engine.Conflicts;

import WhichPlantWhere.Engine.utils.ElementMapper;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class ConflictElementMapper implements ElementMapper {

    @Override
    public Object mapElement(Object elementToMap) {
        Element conflictElement = (Element) elementToMap;
        return new Conflict(conflictElement.getElementsByTagName("veg").item(0).getTextContent(),
                new ArrayList<>());
    }
}