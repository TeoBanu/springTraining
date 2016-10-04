package base.repository;

import base.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StoreRepository {
    private Map<Product, Integer> stock;

    public void setStock(Map<Product, Integer> stock) {
        this.stock = stock;
    }

    public Map<Product, Integer> getStock() {
        return stock;
    }


    public void displayStock() {
        stock.forEach((key,value) -> System.out.println("There are " + value + " " + key.getName() + " on stock"));
    }
}
