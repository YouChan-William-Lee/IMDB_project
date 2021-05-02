package app.model.ShowEntities;

public class ProductionCo {

    private final int id;
    private final String name;

    //Production company constructor
    public ProductionCo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
