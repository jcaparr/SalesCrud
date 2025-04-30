package com.todocode.bazar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleSummaryDto {
    private Long saleCode;
    private LocalDate saleDate;
    private Double total;
}
