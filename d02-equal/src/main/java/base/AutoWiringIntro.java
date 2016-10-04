package base;

import base.controller.ProductController;
import base.controller.StoreController;
import base.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demonstrates the wiring of an {@link ApplicationContext} using a auto-wired beans
 */
public class AutoWiringIntro {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final ProductController productController = applicationContext.getBean(ProductController.class);

        /*
        productRepository.displayTheBooleanValue();
        productRepository.displayTheInt();
        */
//        System.out.println("Product with id = 2 is " + productController.getProduct(2).getName());
        productController.displayProducts();
        final StoreController storeController = applicationContext.getBean(StoreController.class);
        System.out.println("Displaying stock: ");
//        storeController.displayStock();
    }
}
