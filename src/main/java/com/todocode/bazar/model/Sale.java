package com.todocode.bazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long saleCode;
    private LocalDate saleDate;
    private Double total;
    @OneToMany
    private List<Product> productList;
    @OneToOne
    @JoinColumn(name = "idClient", referencedColumnName = "id")
    private Client client;
}
