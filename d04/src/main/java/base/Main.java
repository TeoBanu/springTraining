package base;

import base.model.Product;
import base.repository.ProductRepository;
import base.repository.StoreRepository;
import base.service.ProductService;
import base.service.StoreService;
import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by banut on 06/10/2016.
 */
public class Main {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(final String[] args) {
        ConnectionSingleton connectionSingleton = ConnectionSingleton.getInstance();
        final ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        Product p = new Product();
        p.setName("skirt");
        p.setStock(4);
        p.setSectionId(4);
        productService.createProduct(p);
        p = productService.getProduct(1);
        System.out.println("Product: " + p.getId() + " " + p.getName() + " " + p.getSectionId() + " " + p.getStock());
        productService.updateProduct(3, 1);
        productRepository.deleteProduct(2);

        final StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);
        List<String> stocks = storeService.getStockForAllProducts(3);
        stocks.forEach((stock) -> System.out.println(stock));
    }
}
