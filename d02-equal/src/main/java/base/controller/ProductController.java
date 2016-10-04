package base.controller;

import base.model.Product;
import base.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Product getProduct(final int id) {
        return productService.getProduct(id);
    }

    public void displayProducts() {
        productService.displayProducts();
    }
}
