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
        ResultSet resultSet = storeService.getStockForAllProducts(3);

        try {
            while (resultSet.next() == true) {
                System.out.println();
                System.out.println("Store name: " + resultSet.getString(1));
                System.out.println("Section name: " + resultSet.getString(2));
                System.out.println("Product name: " + resultSet.getString(3));
                System.out.println("Stock: " + resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
