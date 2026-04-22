package com.nutrireceitas.api.service;

import com.nutrireceitas.api.dto.ReceitaDTO;
import com.nutrireceitas.api.dto.ReceitaRequestDTO;

import java.util.List;

public interface IReceitaService {
    List<ReceitaDTO> listarTodas();
    List<ReceitaDTO> listarPublicas();
    ReceitaDTO buscarPorId(Long id);
    ReceitaDTO criar(ReceitaRequestDTO dto);
    ReceitaDTO atualizar(Long id, ReceitaRequestDTO dto);
    void excluir(Long id);
}
