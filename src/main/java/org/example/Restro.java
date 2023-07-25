package org.example;

import java.util.ArrayList;
import java.util.List;

public class Restro
{
    private String id;
    private String name;
    private Location location;
    private List<Dish> menu;

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public String getId() {
        return id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restro{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", menu=" + menu +
                '}';
    }
}
