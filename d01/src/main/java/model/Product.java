package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by banut on 03/10/2016.
 */
public class Product implements Serializable {
    private long id;
    private String name;
    private double price;
    private String producer;
    private String descr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(producer, product.producer) &&
                Objects.equals(descr, product.descr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, producer, descr);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", producer='" + producer + '\'' +
                ", descr='" + descr + '\'' +
                '}';
    }
}
