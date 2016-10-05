package base.repository;

import base.model.Product;
import base.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProductRepository {
    private final FileHandler fileHandler;
    private List<Product> products = new ArrayList<>();

    @Autowired
    public ProductRepository(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void createProducts() {
        List<String> lines = fileHandler.read("resources/products.csv");
        lines.forEach((line) -> createProduct(line));
    }

    private void createProduct(String line) {
        String[] lineSplit = line.split(",");

        Product product = new Product();
        product.setId(Integer.parseInt(lineSplit[0]));
        product.setName(lineSplit[1]);
        product.setProducer(lineSplit[2]);
        product.setDescr(lineSplit[3]);
        product.setPrice(Double.parseDouble(lineSplit[4]));

        products.add(product);

    }

    public List<Product> getProducts() {
        return products;
    }

    public double totalPriceOfAllProducts() {
        return products.stream()
                       .mapToDouble((product) -> product.getPrice())
                       .sum();
    }

    public void displayAll() {
        products.forEach((product) -> System.out.println(product));
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int id) {
        products.removeIf((product) -> product.getId() == id);
    }

    public void updateProduct(Product product) {
        this.deleteProduct(product.getId());
        products.add(product);
    }
}
