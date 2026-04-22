package com.nutrireceitas.api.dto;

import com.nutrireceitas.api.enums.DificuldadeReceita;
import com.nutrireceitas.api.enums.SegmentoReceita;
import com.nutrireceitas.api.enums.StatusReceita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

// DTO de requisição (o que o frontend envia ao backend)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReceitaRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Segmento é obrigatório")
    private SegmentoReceita segmento;

    private String descricao;
    private String urlImagem;
    private StatusReceita status;
    private Integer calorias;
    private Integer porcaoGramas;
    private Integer rendimentoPorcoes;
    private Double proteinas;
    private Double carboidratos;
    private Double gorduras;
    private Integer tempoPreparo;
    private DificuldadeReceita dificuldade;
    private List<String> ingredientes;
    private String modoPreparo;
    private List<String> tagsDieteticas;
}
