package app.model.ShowEntities;


// Production company constructor


public class ProductionCo {

    private final int id;

    private final String name;


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
