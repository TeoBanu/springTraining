package base.service;

import base.model.Product;
import base.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(final int id) {
        return productRepository.get(id);
    }

    public void displayProducts() {
        productRepository.displayTheProducts();
    }
}
