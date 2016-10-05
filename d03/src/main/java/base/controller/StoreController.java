package base.controller;

import base.model.Store;
import base.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "store")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/create"
    )
    public
    @ResponseBody
    String createStores() {
        storeService.createStores();
        return "OK";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/get",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public
    @ResponseBody
    List<Store> getStores() {
        return storeService.getStores();
    }



    public void displayStoresWithExpensiveProducts() {
        storeService.displayStoresWithExpensiveProducts();
    }

    public List<Double> totalPriceOfProductsPerStore() {
        return storeService.totalPriceOfProductsPerStore();
    }

    public void displayAllStoreNames() {
        storeService.displayAllStoreNames();
    }

    public void displayAll() {
        storeService.displayAll();
    }
}
