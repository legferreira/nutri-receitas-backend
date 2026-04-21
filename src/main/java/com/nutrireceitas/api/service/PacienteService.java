package com.nutrireceitas.api.service;

import com.nutrireceitas.api.dto.PacienteDTO;
import com.nutrireceitas.api.dto.PacienteRequestDTO;
import com.nutrireceitas.api.dto.RegistroPesoDTO;
import com.nutrireceitas.api.exception.ResourceNotFoundException;
import com.nutrireceitas.api.mapper.PacienteMapper;
import com.nutrireceitas.api.model.Paciente;
import com.nutrireceitas.api.model.RegistroPeso;
import com.nutrireceitas.api.repository.PacienteRepository;
import com.nutrireceitas.api.repository.RegistroPesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService implements IPacienteService {

    private final PacienteRepository pacienteRepository;
    private final RegistroPesoRepository registroPesoRepository;
    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteDTO buscarPorId(Long id) {
        return pacienteMapper.toDTO(encontrarPorId(id));
    }

    @Override
    @Transactional
    public PacienteDTO criar(PacienteRequestDTO dto) {
        Paciente paciente = pacienteMapper.toModel(dto);
        return pacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    @Override
    @Transactional
    public PacienteDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente existente = encontrarPorId(id);
        pacienteMapper.atualizarCampos(existente, dto);
        return pacienteMapper.toDTO(pacienteRepository.save(existente));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrada com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroPesoDTO> listarHistoricoPeso(Long pacienteId) {
        encontrarPorId(pacienteId);
        return registroPesoRepository.findByPacienteIdOrderByDataAsc(pacienteId)
                .stream()
                .map(pacienteMapper::toPesoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PacienteDTO adicionarRegistroPeso(Long pacienteId, RegistroPesoDTO dto) {
        Paciente paciente = encontrarPorId(pacienteId);
        RegistroPeso registro = RegistroPeso.builder()
                .paciente(paciente)
                .data(dto.getData())
                .peso(dto.getPeso())
                .observacao(dto.getObservacao())
                .build();
        registroPesoRepository.save(registro);
        paciente.setPesoAtual(dto.getPeso());
        pacienteRepository.save(paciente);
        return pacienteMapper.toDTO(encontrarPorId(pacienteId));
    }

    @Override
    @Transactional
    public void excluirRegistroPeso(Long pacienteId, Long registroId) {
        encontrarPorId(pacienteId);
        RegistroPeso registro = registroPesoRepository.findById(registroId)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de peso não encontrado com id: " + registroId));
        registroPesoRepository.delete(registro);
    }

    private Paciente encontrarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrada com id: " + id));
    }
}
