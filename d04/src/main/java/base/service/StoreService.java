package base.service;

import base.repository.StoreRepository;

import java.sql.ResultSet;

/**
 * Created by banut on 06/10/2016.
 */
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public ResultSet getStockForAllProducts(int id) {
        return storeRepository.getStockForAllProducts(id);
    }
}
