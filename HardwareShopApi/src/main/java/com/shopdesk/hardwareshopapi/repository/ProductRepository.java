package com.shopdesk.hardwareshopapi.repository;

import com.shopdesk.hardwareshopapi.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
