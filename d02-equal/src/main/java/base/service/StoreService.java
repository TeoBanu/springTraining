package base.service;

import base.model.Product;
import base.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(final StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Map<Product, Integer> getStock() {
        return storeRepository.getStock();
    }


    public void displayStock() {
        storeRepository.displayStock();
    }
}
