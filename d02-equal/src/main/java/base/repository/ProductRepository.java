package base.repository;

import base.model.Product;
import base.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;
    private final FileHandler fileHandler;

    @Autowired
    public ProductRepository(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    private void createProducts() {
        List<String> lines = fileHandler.read();
        lines.forEach((line) -> createProduct(line));
    }

    private void createProduct(String line) {
        String[] lineSplit = line.split(",");

        Product product = new Product();
        product.setId(Integer.parseInt(lineSplit[0]));
        product.setName(lineSplit[1]);

        products.add(product);

    }

    @SuppressWarnings("unused")
    public Product get(int id) {
        final Product product = new Product();
        product.setId(24);
        product.setName("Dell XPS 9350");

        return product;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void displayTheProducts() {
        products.forEach(product -> System.out.println(product.getId() + ", " + product.getName()));
    }

    @PostConstruct
    public void composeProductsList() {
        List<String> fileContent = fileHandler.read();
        this.createProducts();
    }
}
