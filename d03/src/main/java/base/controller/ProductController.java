package base.controller;

import base.model.Product;
import base.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by banut on 04/10/2016.
 */
@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/create"
    )
    public @ResponseBody String createProducts() {
        productService.createProducts();
        return "OK";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/get",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody List<Product> getProducts() {
        return productService.getProducts();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "OK";
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/delete/{id}"
    )
    public @ResponseBody String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "OK";
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody String updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return "OK";
    }


    public double totalPriceOfAllProducts() {
        return productService.totalPriceOfAllProducts();
    }

    public void displayAll() {
        productService.displayAll();
    }
}
