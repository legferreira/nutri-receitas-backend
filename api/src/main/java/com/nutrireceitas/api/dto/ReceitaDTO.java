package com.nutrireceitas.api.dto;

import com.nutrireceitas.api.enums.DificuldadeReceita;
import com.nutrireceitas.api.enums.SegmentoReceita;
import com.nutrireceitas.api.enums.StatusReceita;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

// DTO de resposta (o que o backend envia ao frontend)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReceitaDTO {
    private Long id;
    private String nome;
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
    private LocalDate dataCadastro;
}
