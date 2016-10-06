package base.service;

import base.model.Product;
import base.repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    public void updateProduct(int oldSectionId, int newSectionId) {
        productRepository.updateProduct(oldSectionId, newSectionId);
    }

    public Product getProduct(int id) {
        return productRepository.getProduct(id);
    }
}

