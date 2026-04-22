package com.nutrireceitas.api.service;

import com.nutrireceitas.api.dto.PacienteDTO;
import com.nutrireceitas.api.dto.PacienteRequestDTO;
import com.nutrireceitas.api.dto.RegistroPesoDTO;

import java.util.List;

public interface IPacienteService {
    List<PacienteDTO> listarTodos();
    PacienteDTO buscarPorId(Long id);
    PacienteDTO criar(PacienteRequestDTO dto);
    PacienteDTO atualizar(Long id, PacienteRequestDTO dto);
    void excluir(Long id);
    List<RegistroPesoDTO> listarHistoricoPeso(Long pacienteId);
    PacienteDTO adicionarRegistroPeso(Long pacienteId, RegistroPesoDTO dto);
    void excluirRegistroPeso(Long pacienteId, Long registroId);
}
