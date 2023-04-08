package models;

public abstract class Item {
    private Integer id;
    private String name;
    private String description;
    private Double price;


    public Item() {

    }

    public Item(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                ", name: " + name +
                ", description: " + description +
                ", price: " + price;
    }
}
