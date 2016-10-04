package base.controller;

import base.model.Product;
import base.service.ProductService;
import base.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public Map<Product,Integer> getStock() {
        return storeService.getStock();
    }

    public void displayStock() {
        storeService.displayStock();
    }
}
