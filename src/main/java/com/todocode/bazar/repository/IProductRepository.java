package com.todocode.bazar.repository;

import com.todocode.bazar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    public boolean existsByBrandAndName(String brand, String name);
}
