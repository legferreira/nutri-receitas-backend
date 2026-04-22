package com.nutrireceitas.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "registros_peso")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RegistroPeso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Double peso;

    @Column(columnDefinition = "TEXT")
    private String observacao;
}
