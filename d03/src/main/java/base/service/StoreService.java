package base.service;

import base.model.Product;
import base.model.Store;
import base.repository.StoreRepository;
import base.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StoreService{

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void createStores() {
        storeRepository.createStores();
    }

    public List<Store> getStores() {
        return storeRepository.getStores();
    }

    public void displayStoresWithExpensiveProducts() {
        storeRepository.displayStoresWithExpensiveProducts();
    }

    public List<Double> totalPriceOfProductsPerStore() {
        return storeRepository.totalPriceOfProductsPerStore();
    }

    public void displayAllStoreNames() {
       storeRepository.displayAllStoreNames();
    }

    public void displayAll() {
        storeRepository.displayAll();
    }
}
