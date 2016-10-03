import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ProductHandler;
import service.StoreHandler;

import java.util.List;

/**
 * Created by banut on 03/10/2016.
 */
public class Main {
    public static void main(String[] args) {

        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final ProductHandler productHandler = context.getBean(ProductHandler.class);
        productHandler.createProducts();

        final StoreHandler storeHandler = context.getBean(StoreHandler.class);
        storeHandler.createStores();


        //display
        storeHandler.displayAll();
        productHandler.displayAll();

        System.out.println();
        storeHandler.displayStoresWithExpensiveProducts();

        //sum of all products
        System.out.println();
        System.out.println("Sum of all products: " + productHandler.totalPriceOfAllProducts());

        //compute total cost of products in store
        System.out.println();
        List<Double> sums = storeHandler.totalPriceOfProductsPerStore();
        sums.forEach((s) -> System.out.println("Sum of all products of store: " + s));

        System.out.println();
        System.out.println("All store names with custom collector: ");
        storeHandler.displayAllStoreNames();

    }
}
