package base.service;

import base.model.Product;
import base.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProducts() {
        productRepository.createProducts();
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public double totalPriceOfAllProducts() {
        return productRepository.totalPriceOfAllProducts();
    }

    public void displayAll() {
        productRepository.displayAll();
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }
}
