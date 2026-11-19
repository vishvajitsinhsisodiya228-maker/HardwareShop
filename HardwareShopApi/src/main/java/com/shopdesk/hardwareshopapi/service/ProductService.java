package com.shopdesk.hardwareshopapi.service;

import com.shopdesk.hardwareshopapi.model.Product;
import com.shopdesk.hardwareshopapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        product.setId(null);   // ignore any id sent by the client; the database assigns it
        return productRepository.save(product);
    }

    /** Replaces the fields of an existing product; empty if the id is unknown. */
    public Optional<Product> updateProduct(Long id, Product details) {
        Optional<Product> found = productRepository.findById(id);
        if (!found.isPresent()) {
            return Optional.empty();
        }
        Product existing = found.get();
        existing.setProductName(details.getProductName());
        existing.setCategory(details.getCategory());
        existing.setQuantity(details.getQuantity());
        existing.setUnitPrice(details.getUnitPrice());
        return Optional.of(productRepository.save(existing));
    }

    /** Deletes a product; false if the id is unknown. */
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    /**
     * 404 if the product does not exist, 409 if the quantity asked is more than
     * the stock, otherwise reduces the stock, saves and returns the product (200).
     */
    public ResponseEntity<Product> sellProduct(Long id, int quantity) {
        Optional<Product> found = productRepository.findById(id);
        if (!found.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (quantity <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Product product = found.get();
        if (quantity > product.getQuantity()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        product.setQuantity(product.getQuantity() - quantity);
        return ResponseEntity.ok(productRepository.save(product));
    }
}
