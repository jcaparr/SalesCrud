package com.todocode.bazar.dto.request;

import com.todocode.bazar.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class SaleRequestDto {
    @NotBlank(message = "Date cannot be blank")
    private LocalDate saleDate;

    @NotNull(message = "Total cannot be null")
    @PositiveOrZero(message = "The total must be positive or zero")
    private Double total;

    private List<ProductRequestDto> productList;
    private Client client;
}
