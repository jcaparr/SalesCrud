package com.todocode.bazar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientSummaryDto {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
}
