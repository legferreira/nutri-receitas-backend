package com.nutrireceitas.api.service;

import com.nutrireceitas.api.dto.ReceitaDTO;
import com.nutrireceitas.api.dto.ReceitaRequestDTO;
import com.nutrireceitas.api.enums.StatusReceita;
import com.nutrireceitas.api.exception.ResourceNotFoundException;
import com.nutrireceitas.api.model.Receita;
import com.nutrireceitas.api.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public List<ReceitaDTO> listarTodas() {
        return receitaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ReceitaDTO> listarPublicas() {
        return receitaRepository.findByStatus(StatusReceita.PUBLICADA)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReceitaDTO buscarPorId(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
        return toDTO(receita);
    }

    public ReceitaDTO criar(ReceitaRequestDTO dto) {
        Receita receita = toModel(dto);
        return toDTO(receitaRepository.save(receita));
    }

    public ReceitaDTO atualizar(Long id, ReceitaRequestDTO dto) {
        Receita existente = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
        atualizarCampos(existente, dto);
        return toDTO(receitaRepository.save(existente));
    }

    public void excluir(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receita não encontrada com id: " + id);
        }
        receitaRepository.deleteById(id);
    }

    // --- Mapeamentos ---

    private ReceitaDTO toDTO(Receita r) {
        return ReceitaDTO.builder()
                .id(r.getId())
                .nome(r.getNome())
                .segmento(r.getSegmento())
                .descricao(r.getDescricao())
                .urlImagem(r.getUrlImagem())
                .status(r.getStatus())
                .calorias(r.getCalorias())
                .porcaoGramas(r.getPorcaoGramas())
                .rendimentoPorcoes(r.getRendimentoPorcoes())
                .proteinas(r.getProteinas())
                .carboidratos(r.getCarboidratos())
                .gorduras(r.getGorduras())
                .tempoPreparo(r.getTempoPreparo())
                .dificuldade(r.getDificuldade())
                .ingredientes(r.getIngredientes())
                .modoPreparo(r.getModoPreparo())
                .tagsDieteticas(r.getTagsDieteticas())
                .dataCadastro(r.getDataCadastro())
                .build();
    }

    private Receita toModel(ReceitaRequestDTO dto) {
        return Receita.builder()
                .nome(dto.getNome())
                .segmento(dto.getSegmento())
                .descricao(dto.getDescricao())
                .urlImagem(dto.getUrlImagem())
                .status(dto.getStatus() != null ? dto.getStatus() : StatusReceita.RASCUNHO)
                .calorias(dto.getCalorias())
                .porcaoGramas(dto.getPorcaoGramas())
                .rendimentoPorcoes(dto.getRendimentoPorcoes())
                .proteinas(dto.getProteinas())
                .carboidratos(dto.getCarboidratos())
                .gorduras(dto.getGorduras())
                .tempoPreparo(dto.getTempoPreparo())
                .dificuldade(dto.getDificuldade())
                .ingredientes(dto.getIngredientes())
                .modoPreparo(dto.getModoPreparo())
                .tagsDieteticas(dto.getTagsDieteticas())
                .build();
    }

    private void atualizarCampos(Receita r, ReceitaRequestDTO dto) {
        r.setNome(dto.getNome());
        r.setSegmento(dto.getSegmento());
        r.setDescricao(dto.getDescricao());
        r.setUrlImagem(dto.getUrlImagem());
        if (dto.getStatus() != null) r.setStatus(dto.getStatus());
        r.setCalorias(dto.getCalorias());
        r.setPorcaoGramas(dto.getPorcaoGramas());
        r.setRendimentoPorcoes(dto.getRendimentoPorcoes());
        r.setProteinas(dto.getProteinas());
        r.setCarboidratos(dto.getCarboidratos());
        r.setGorduras(dto.getGorduras());
        r.setTempoPreparo(dto.getTempoPreparo());
        r.setDificuldade(dto.getDificuldade());
        r.setIngredientes(dto.getIngredientes());
        r.setModoPreparo(dto.getModoPreparo());
        r.setTagsDieteticas(dto.getTagsDieteticas());
    }
}
