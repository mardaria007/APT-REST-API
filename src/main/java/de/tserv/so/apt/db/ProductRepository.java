package de.tserv.so.apt.db;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tserv.so.apt.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
