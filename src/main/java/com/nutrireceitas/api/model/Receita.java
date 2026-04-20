package com.nutrireceitas.api.model;

import com.nutrireceitas.api.enums.DificuldadeReceita;
import com.nutrireceitas.api.enums.SegmentoReceita;
import com.nutrireceitas.api.enums.StatusReceita;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receitas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SegmentoReceita segmento;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 300)
    private String urlImagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReceita status;

    private Integer calorias;
    private Integer porcaoGramas;
    private Integer rendimentoPorcoes;
    private Double proteinas;
    private Double carboidratos;
    private Double gorduras;
    private Integer tempoPreparo;

    @Enumerated(EnumType.STRING)
    private DificuldadeReceita dificuldade;

    @ElementCollection
    @CollectionTable(name = "receita_ingredientes", joinColumns = @JoinColumn(name = "receita_id"))
    @Column(name = "ingrediente", columnDefinition = "TEXT")
    private List<String> ingredientes;

    @Column(columnDefinition = "TEXT")
    private String modoPreparo;

    @ElementCollection
    @CollectionTable(name = "receita_tags", joinColumns = @JoinColumn(name = "receita_id"))
    @Column(name = "tag")
    private List<String> tagsDieteticas;

    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDate.now();
        }
        if (this.status == null) {
            this.status = StatusReceita.RASCUNHO;
        }
    }
}
