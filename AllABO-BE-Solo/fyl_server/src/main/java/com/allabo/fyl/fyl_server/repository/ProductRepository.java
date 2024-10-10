package com.allabo.fyl.fyl_server.repository;

import com.allabo.fyl.fyl_server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}
