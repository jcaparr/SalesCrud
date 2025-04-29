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
public class UpdateClientDto {
    @NotBlank(message = "The client name cannot be null")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "The clietn name must start with mayus")
    private String name;

    @NotBlank(message = "The last name cannot be null")
    private String lastName;

    @NotBlank(message = "The dni cannot be null")
    @Pattern(regexp="^\\d{8}$", message = "The DNI must have 8 digits, only numbers allowed")
    private String dni;
}
