package com.todocode.bazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productCode;
    private String name;
    private String brand;
    private Double cost;
    private Double availableQuantity;

    @ManyToMany(mappedBy = "productList")
    private List<Sale> sales;
}
