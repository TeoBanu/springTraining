package base.repository;

import base.model.Store;
import base.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class StoreRepository {
   // private final ProductHandler productHandler;
    private List<Store> stores = new ArrayList<>();
    private final FileHandler fileHandler;

    @Autowired
    public StoreRepository(FileHandler fileHandler){//, ProductHandler productHandler) {

        this.fileHandler = fileHandler;
       // this.productHandler = productHandler;
    }

    public void createStores() {
        List<String> lines = fileHandler.read("resources/stores.csv");
        lines.forEach((line) -> createStore(line));
        //addProductsToStores();
    }

    private void createStore(String line) {
        String[] lineSplit = line.split(",");

        Store store = new Store();
        store.setId(Long.parseLong(lineSplit[0]));
        store.setName(lineSplit[1]);
        store.setAddress(lineSplit[2]);

        stores.add(store);
    }

    public List<Store> getStores() {
        return stores;
    }

    public void displayStoresWithExpensiveProducts() {
        //filter stores that have products with price greater than 500:
        System.out.println("Stores that have products with price greater than 500: ");
        List<Store> expensiveProductStores = stores.stream().filter((store) -> store.getProducts()
                                                                                    .stream()
                                                                                    .filter((product) -> product.getPrice() > 500).count() > 0).collect(Collectors.toList());
        expensiveProductStores.forEach((store) -> System.out.println(store));
    }


    public List<Double> totalPriceOfProductsPerStore() {
        return stores.stream()
                     .map((store) -> store.getProducts().stream()
                                          .mapToDouble((product) -> product.getPrice())
                                          .sum())
                     .collect(Collectors.toList());
    }

    public void displayAllStoreNames() {
        Collector<Store, StringJoiner, String> storeNameCollector =
                Collector.of(
                        () -> new StringJoiner(" & "),
                        (joiner, store) -> joiner.add(store.getName().toUpperCase()),
                        (joiner1, joiner2) -> joiner1.merge(joiner2),
                        (joiner) -> joiner.toString());

        String storeNames = stores.stream().collect(storeNameCollector);

        System.out.println(storeNames);
    }

    public void displayAll() {
        System.out.println("All stores:");
        stores.forEach((store) -> System.out.println(store));
    }

//    private void addProductsToStores() {
//        //add products to stores
//        List<Product> productsOfStore1 = new ArrayList<>();
//        productsOfStore1.add(productHandler.getProducts().get(0));
//        productsOfStore1.add(productHandler.getProducts().get(3));
//        List<Product> productsOfStore2 = new ArrayList<>();
//        productsOfStore2.add(productHandler.getProducts().get(1));
//        productsOfStore2.add(productHandler.getProducts().get(2));
//        stores.get(0).setProducts(productsOfStore1);
//        stores.get(1).setProducts(productsOfStore2);
//    }


}
