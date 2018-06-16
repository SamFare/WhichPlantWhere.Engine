package WhichPlantWhere.Engine.PlantLoader;

public class Plant {
    private  String name;
    private String imgUrl;

    Plant(String name, String imgUrl ) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }
}
