package com.nutrireceitas.api.service;

import com.nutrireceitas.api.dto.ReceitaDTO;
import com.nutrireceitas.api.dto.ReceitaRequestDTO;
import com.nutrireceitas.api.enums.StatusReceita;
import com.nutrireceitas.api.exception.ResourceNotFoundException;
import com.nutrireceitas.api.mapper.ReceitaMapper;
import com.nutrireceitas.api.model.Receita;
import com.nutrireceitas.api.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceitaService implements IReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ReceitaMapper receitaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ReceitaDTO> listarTodas() {
        return receitaRepository.findAll()
                .stream()
                .map(receitaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceitaDTO> listarPublicas() {
        return receitaRepository.findByStatus(StatusReceita.PUBLICADA)
                .stream()
                .map(receitaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReceitaDTO buscarPorId(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
        return receitaMapper.toDTO(receita);
    }

    @Override
    @Transactional
    public ReceitaDTO criar(ReceitaRequestDTO dto) {
        Receita receita = receitaMapper.toModel(dto);
        return receitaMapper.toDTO(receitaRepository.save(receita));
    }

    @Override
    @Transactional
    public ReceitaDTO atualizar(Long id, ReceitaRequestDTO dto) {
        Receita existente = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
        receitaMapper.atualizarCampos(existente, dto);
        return receitaMapper.toDTO(receitaRepository.save(existente));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receita não encontrada com id: " + id);
        }
        receitaRepository.deleteById(id);
    }
}
