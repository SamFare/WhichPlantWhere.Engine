package WhichPlantWhere.Engine.PlantLoader;

import WhichPlantWhere.Engine.Exceptions.PlantLoaderException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class PlantLoader {
    public static ArrayList<Plant> loadFrom(String fileToLoadFrom )  throws PlantLoaderException {
        try {
            Document document = DocumentBuilderFactory
                                    .newInstance()
                                    .newDocumentBuilder()
                                    .parse(new File(fileToLoadFrom));
            ArrayList<Plant> plants = new ArrayList<>();

            for(int i = 0; i < document.getElementsByTagName("veg").getLength(); ++i ) {
                Element veg =  (Element) document.getElementsByTagName("veg").item(i);
                plants.add(buildPlantFromXmlElement(veg));
            }

            return plants;


        } catch(Exception e) {
            throw new PlantLoaderException();
        }
    }

    private static Plant buildPlantFromXmlElement(Element plantElemennt) {
       return  new Plant(
               plantElemennt.getElementsByTagName("name").item(0).getTextContent(),
               plantElemennt.getElementsByTagName("imgUrl").item(0).getTextContent());
    }
}
