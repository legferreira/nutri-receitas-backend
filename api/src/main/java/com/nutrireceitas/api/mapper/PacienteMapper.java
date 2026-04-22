package com.nutrireceitas.api.mapper;

import com.nutrireceitas.api.dto.PacienteDTO;
import com.nutrireceitas.api.dto.PacienteRequestDTO;
import com.nutrireceitas.api.dto.RegistroPesoDTO;
import com.nutrireceitas.api.enums.StatusPaciente;
import com.nutrireceitas.api.model.Paciente;
import com.nutrireceitas.api.model.RegistroPeso;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PacienteMapper {

    public PacienteDTO toDTO(Paciente paciente) {
        List<RegistroPesoDTO> historico = paciente.getHistoricoPeso() != null
                ? paciente.getHistoricoPeso().stream().map(this::toPesoDTO).collect(Collectors.toList())
                : new java.util.ArrayList<>();

        return PacienteDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .dataNascimento(paciente.getDataNascimento())
                .email(paciente.getEmail())
                .telefone(paciente.getTelefone())
                .pesoAtual(paciente.getPesoAtual())
                .altura(paciente.getAltura())
                .pesoMeta(paciente.getPesoMeta())
                .dataConsulta(paciente.getDataConsulta())
                .objetivo(paciente.getObjetivo())
                .status(paciente.getStatus())
                .restricoesAlimentares(paciente.getRestricoesAlimentares())
                .observacoes(paciente.getObservacoes())
                .historicoPeso(historico)
                .build();
    }

    public RegistroPesoDTO toPesoDTO(RegistroPeso registro) {
        return RegistroPesoDTO.builder()
                .id(registro.getId())
                .data(registro.getData())
                .peso(registro.getPeso())
                .observacao(registro.getObservacao())
                .build();
    }

    public Paciente toModel(PacienteRequestDTO dto) {
        return Paciente.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .pesoAtual(dto.getPesoAtual())
                .altura(dto.getAltura())
                .pesoMeta(dto.getPesoMeta())
                .dataConsulta(dto.getDataConsulta())
                .objetivo(dto.getObjetivo())
                .status(dto.getStatus() != null ? dto.getStatus() : StatusPaciente.ATIVA)
                .restricoesAlimentares(dto.getRestricoesAlimentares() != null ? dto.getRestricoesAlimentares() : new java.util.ArrayList<>())
                .observacoes(dto.getObservacoes())
                .build();
    }

    public void atualizarCampos(Paciente paciente, PacienteRequestDTO dto) {
        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());
        paciente.setPesoAtual(dto.getPesoAtual());
        paciente.setAltura(dto.getAltura());
        paciente.setPesoMeta(dto.getPesoMeta());
        paciente.setDataConsulta(dto.getDataConsulta());
        paciente.setObjetivo(dto.getObjetivo());
        if (dto.getStatus() != null) paciente.setStatus(dto.getStatus());
        paciente.setRestricoesAlimentares(dto.getRestricoesAlimentares() != null ? dto.getRestricoesAlimentares() : new java.util.ArrayList<>());
        paciente.setObservacoes(dto.getObservacoes());
    }
}
