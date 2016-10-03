package service;

import model.Product;
import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by banut on 03/10/2016.
 */
public class ProductHandler implements IDisplayer{
    private final FileHandler fileHandler;
    private List<Product> products = new ArrayList<>();

    public ProductHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void createProducts() {
        List<String> lines = fileHandler.read();
        lines.forEach((line) -> createProduct(line));
    }

    private void createProduct(String line) {
        String[] lineSplit = line.split(",");

        Product product = new Product();
        product.setId(Long.parseLong(lineSplit[0]));
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

    @Override
    public void displayAll() {
        System.out.println("All products:");
        products.forEach((product) -> System.out.println(product));
    }
}
