package com.example.springproject2.controller;

import com.example.springproject2.entity.Product;
import com.example.springproject2.entity.ProductToBePatched;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final List<Product> products = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET, path = "/api/product")
    public Product getCustomProduct() {

        return new Product("name1", 10.9, 1);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/products")
    public List<Product> getAllCustomProducts() {

        Product product1 = new Product("name1", 10.9, 1);
        Product product2 = new Product("name2", 19.9, 6);
        Product product3 = new Product("name3", 1.95, 8);

        return List.of(product1, product2, product3);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/product")
    public void addProduct(@RequestBody Product product) {

        products.add(product);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/allProduct")
    public List<Product> getProducts() {
        return products;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/product/{name}/{price}")
    public Product getProductByName(@PathVariable String name, @PathVariable Double price) {

        Optional<Product> product = products.stream().filter(p -> p.getName().equals(name) && p.getPrice().equals(price)).findFirst();

        if (product.isPresent()) {
            return product.get();
        } else {
            return null;
        }
    }

    @ResponseStatus
    @RequestMapping(method = RequestMethod.GET, path = "/api/productsByPriceAndQuantity")
    private Product getProductByPriceAndQuantity(@RequestParam Double price, @RequestParam Integer quantity) {

        Optional<Product> product = products.stream().filter(p -> p.getPrice().equals(price) && p.getQuantity().equals(quantity)).findFirst();

        if (product.isPresent()) {
            return product.get();
        } else {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/product/{index}")
    public void updateProduct(@PathVariable Integer index, @RequestBody Product product) {

//        if (index > products.size() -1) {
//            return new Exception("");
//        }

        Product productToBeUpdated = products.get(index);

        productToBeUpdated.setName(product.getName());
        productToBeUpdated.setQuantity(product.getQuantity());
        productToBeUpdated.setPrice(product.getPrice());
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/api/product/{index}")
    public void patchProduct(@PathVariable Integer index, @RequestBody ProductToBePatched product) {

        Product productToBePatched = products.get(index);

        if (product.getPrice() != null) {
            productToBePatched.setPrice(product.getPrice());
        }
        if (product.getQuantity() != null) {
            productToBePatched.setQuantity(product.getQuantity());
        }
        products.set(index, productToBePatched);

        ResponseEntity.ok("Product patched successfully");
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/product/{index}")
    public void deleteProduct(@PathVariable Integer index) {

        if (index >= 0 && index < products.size()) {
            products.remove((int) index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBoundsEx(IndexOutOfBoundsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }
}
