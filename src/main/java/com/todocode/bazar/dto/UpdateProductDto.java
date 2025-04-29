package com.todocode.bazar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductDto {
    @NotBlank(message = "The product name cannot be null")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "The product name must start with mayus")
    private String name;

    @NotBlank(message = "The brand name cannot be null")
    private String brand;

    @NotNull(message = "The cost cannot be null")
    @PositiveOrZero(message = "The cost must be positive or zero")
    private Double cost;

    @NotNull(message = "The available quantity cannot be null")
    @PositiveOrZero(message = "The available quantity must be positive or zero")
    private Double availableQuantity;
}
