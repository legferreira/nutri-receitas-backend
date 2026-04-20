package com.nutrireceitas.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RegistroPesoDTO {
    private Long id;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Peso é obrigatório")
    private Double peso;

    private String observacao;
}
