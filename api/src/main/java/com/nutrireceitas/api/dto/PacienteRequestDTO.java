package com.nutrireceitas.api.dto;

import com.nutrireceitas.api.enums.StatusPaciente;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PacienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private Double pesoAtual;
    private Integer altura;
    private Double pesoMeta;
    private LocalDate dataConsulta;
    private String objetivo;
    private StatusPaciente status;
    private List<String> restricoesAlimentares;
    private String observacoes;
}
