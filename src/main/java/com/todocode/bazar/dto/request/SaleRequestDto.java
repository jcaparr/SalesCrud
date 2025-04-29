package com.todocode.bazar.dto.request;

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
    @NotNull(message = "Date cannot be blank")
    private LocalDate saleDate;

    @NotNull(message = "Total cannot be null")
    @PositiveOrZero(message = "The total must be positive or zero")
    private Double total;

    @NotNull(message = "The list of products must not be null")
    private List<Long> productsIds;

    @NotNull(message = "The client id must not be null")
    private Long client;
}
