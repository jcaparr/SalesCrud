package com.todocode.bazar.dto.response;

import com.todocode.bazar.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDto {
    private Long saleCode;
    private LocalDate saleDate;
    private Double total;
    private List<ProductResponseDto> productList;
    private Client client;
}
