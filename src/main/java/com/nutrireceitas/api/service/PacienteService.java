package com.nutrireceitas.api.service;

import com.nutrireceitas.api.dto.PacienteDTO;
import com.nutrireceitas.api.dto.PacienteRequestDTO;
import com.nutrireceitas.api.dto.RegistroPesoDTO;
import com.nutrireceitas.api.enums.StatusPaciente;
import com.nutrireceitas.api.exception.ResourceNotFoundException;
import com.nutrireceitas.api.model.Paciente;
import com.nutrireceitas.api.model.RegistroPeso;
import com.nutrireceitas.api.repository.PacienteRepository;
import com.nutrireceitas.api.repository.RegistroPesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final RegistroPesoRepository registroPesoRepository;

    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO buscarPorId(Long id) {
        return toDTO(encontrarPorId(id));
    }

    public PacienteDTO criar(PacienteRequestDTO dto) {
        Paciente paciente = toModel(dto);
        return toDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente existente = encontrarPorId(id);
        atualizarCampos(existente, dto);
        return toDTO(pacienteRepository.save(existente));
    }

    public void excluir(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrada com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    // --- Histórico de peso ---

    public List<RegistroPesoDTO> listarHistoricoPeso(Long pacienteId) {
        encontrarPorId(pacienteId);
        return registroPesoRepository.findByPacienteIdOrderByDataAsc(pacienteId)
                .stream()
                .map(this::toPesoDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO adicionarRegistroPeso(Long pacienteId, RegistroPesoDTO dto) {
        Paciente paciente = encontrarPorId(pacienteId);
        RegistroPeso registro = RegistroPeso.builder()
                .paciente(paciente)
                .data(dto.getData())
                .peso(dto.getPeso())
                .observacao(dto.getObservacao())
                .build();
        registroPesoRepository.save(registro);
        // Atualiza peso atual do paciente com o registro mais recente
        paciente.setPesoAtual(dto.getPeso());
        pacienteRepository.save(paciente);
        return toDTO(encontrarPorId(pacienteId));
    }

    public void excluirRegistroPeso(Long pacienteId, Long registroId) {
        encontrarPorId(pacienteId);
        RegistroPeso registro = registroPesoRepository.findById(registroId)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de peso não encontrado com id: " + registroId));
        registroPesoRepository.delete(registro);
    }

    // --- Métodos auxiliares ---

    private Paciente encontrarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrada com id: " + id));
    }

    private PacienteDTO toDTO(Paciente p) {
        List<RegistroPesoDTO> historico = p.getHistoricoPeso()
                .stream()
                .map(this::toPesoDTO)
                .collect(Collectors.toList());

        return PacienteDTO.builder()
                .id(p.getId())
                .nome(p.getNome())
                .dataNascimento(p.getDataNascimento())
                .email(p.getEmail())
                .telefone(p.getTelefone())
                .pesoAtual(p.getPesoAtual())
                .altura(p.getAltura())
                .pesoMeta(p.getPesoMeta())
                .dataConsulta(p.getDataConsulta())
                .objetivo(p.getObjetivo())
                .status(p.getStatus())
                .restricoesAlimentares(p.getRestricoesAlimentares())
                .observacoes(p.getObservacoes())
                .historicoPeso(historico)
                .build();
    }

    private RegistroPesoDTO toPesoDTO(RegistroPeso r) {
        return RegistroPesoDTO.builder()
                .id(r.getId())
                .data(r.getData())
                .peso(r.getPeso())
                .observacao(r.getObservacao())
                .build();
    }

    private Paciente toModel(PacienteRequestDTO dto) {
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
                .restricoesAlimentares(dto.getRestricoesAlimentares())
                .observacoes(dto.getObservacoes())
                .build();
    }

    private void atualizarCampos(Paciente p, PacienteRequestDTO dto) {
        p.setNome(dto.getNome());
        p.setDataNascimento(dto.getDataNascimento());
        p.setEmail(dto.getEmail());
        p.setTelefone(dto.getTelefone());
        p.setPesoAtual(dto.getPesoAtual());
        p.setAltura(dto.getAltura());
        p.setPesoMeta(dto.getPesoMeta());
        p.setDataConsulta(dto.getDataConsulta());
        p.setObjetivo(dto.getObjetivo());
        if (dto.getStatus() != null) p.setStatus(dto.getStatus());
        p.setRestricoesAlimentares(dto.getRestricoesAlimentares());
        p.setObservacoes(dto.getObservacoes());
    }
}
