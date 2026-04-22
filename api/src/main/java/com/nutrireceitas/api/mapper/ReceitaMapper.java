package com.nutrireceitas.api.mapper;

import com.nutrireceitas.api.dto.ReceitaDTO;
import com.nutrireceitas.api.dto.ReceitaRequestDTO;
import com.nutrireceitas.api.enums.StatusReceita;
import com.nutrireceitas.api.model.Receita;
import org.springframework.stereotype.Component;

@Component
public class ReceitaMapper {

    public ReceitaDTO toDTO(Receita receita) {
        return ReceitaDTO.builder()
                .id(receita.getId())
                .nome(receita.getNome())
                .segmento(receita.getSegmento())
                .descricao(receita.getDescricao())
                .urlImagem(receita.getUrlImagem())
                .status(receita.getStatus())
                .calorias(receita.getCalorias())
                .porcaoGramas(receita.getPorcaoGramas())
                .rendimentoPorcoes(receita.getRendimentoPorcoes())
                .proteinas(receita.getProteinas())
                .carboidratos(receita.getCarboidratos())
                .gorduras(receita.getGorduras())
                .tempoPreparo(receita.getTempoPreparo())
                .dificuldade(receita.getDificuldade())
                .ingredientes(receita.getIngredientes())
                .modoPreparo(receita.getModoPreparo())
                .tagsDieteticas(receita.getTagsDieteticas())
                .dataCadastro(receita.getDataCadastro())
                .build();
    }

    public Receita toModel(ReceitaRequestDTO dto) {
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
                .ingredientes(dto.getIngredientes() != null ? dto.getIngredientes() : new java.util.ArrayList<>())
                .modoPreparo(dto.getModoPreparo())
                .tagsDieteticas(dto.getTagsDieteticas() != null ? dto.getTagsDieteticas() : new java.util.ArrayList<>())
                .build();
    }

    public void atualizarCampos(Receita receita, ReceitaRequestDTO dto) {
        receita.setNome(dto.getNome());
        receita.setSegmento(dto.getSegmento());
        receita.setDescricao(dto.getDescricao());
        receita.setUrlImagem(dto.getUrlImagem());
        if (dto.getStatus() != null) receita.setStatus(dto.getStatus());
        receita.setCalorias(dto.getCalorias());
        receita.setPorcaoGramas(dto.getPorcaoGramas());
        receita.setRendimentoPorcoes(dto.getRendimentoPorcoes());
        receita.setProteinas(dto.getProteinas());
        receita.setCarboidratos(dto.getCarboidratos());
        receita.setGorduras(dto.getGorduras());
        receita.setTempoPreparo(dto.getTempoPreparo());
        receita.setDificuldade(dto.getDificuldade());
        receita.setIngredientes(dto.getIngredientes() != null ? dto.getIngredientes() : new java.util.ArrayList<>());
        receita.setModoPreparo(dto.getModoPreparo());
        receita.setTagsDieteticas(dto.getTagsDieteticas() != null ? dto.getTagsDieteticas() : new java.util.ArrayList<>());
    }
}
