package org.example;

public class Dish
{
    private String id;
    private String name;
    private String restroId;
    private float price;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getRestroId() {
        return restroId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRestroId(String restroId) {
        this.restroId = restroId;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", restroId='" + restroId + '\'' +
                ", price=" + price +
                '}';
    }
}
