package com.todocode.bazar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long productCode;
    private String name;
    private String brand;
    private Double cost;
    private Double availableQuantity;
}
