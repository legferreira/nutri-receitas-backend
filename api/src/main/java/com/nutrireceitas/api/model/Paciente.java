package com.nutrireceitas.api.model;

import com.nutrireceitas.api.enums.StatusPaciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    private LocalDate dataNascimento;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String telefone;

    private Double pesoAtual;
    private Integer altura;
    private Double pesoMeta;
    private LocalDate dataConsulta;

    @Column(length = 100)
    private String objetivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPaciente status;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "paciente_restricoes", joinColumns = @JoinColumn(name = "paciente_id"))
    @Column(name = "restricao")
    private List<String> restricoesAlimentares = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Builder.Default
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("data ASC")
    private List<RegistroPeso> historicoPeso = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = StatusPaciente.ATIVA;
        }
    }
}
