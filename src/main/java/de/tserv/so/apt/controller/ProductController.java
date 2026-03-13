package de.tserv.so.apt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tserv.so.apt.db.ProductRepository;
import de.tserv.so.apt.entity.Product;
import de.tserv.so.apt.exceptions.ResourceNotFoundException;

@RestController
public class ProductController {
    private final ProductRepository repository; 

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products") 
    List<Product> all() {
        return repository.findAll(); 
    }

    @GetMapping("/products/{id}")
    Product one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, "products"));
    }

    @PostMapping("/products") 
    Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return repository.findById(id)
            .map(product -> {
                product.setProductExternalId(newProduct.getProductExternalId());
                product.setProduct_link(newProduct.getProduct_link());
                product.setProduct_name(newProduct.getProduct_name());
                product.setVersions(newProduct.getVersions());
                return repository.save(product);
            })
            .orElseGet(() -> {
                return repository.save(newProduct);
            });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
