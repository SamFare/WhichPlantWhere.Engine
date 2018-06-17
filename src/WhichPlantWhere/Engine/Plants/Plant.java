package WhichPlantWhere.Engine.Plants;

public class Plant {
    private  String name;
    private String imgUrl;

    public Plant(String name, String imgUrl ) {
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
