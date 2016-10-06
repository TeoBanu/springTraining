package base.model;

import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private int sectionId;
    private int stock;


    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
