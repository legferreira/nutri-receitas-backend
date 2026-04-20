package com.nutrireceitas.api.controller;

import com.nutrireceitas.api.dto.ReceitaDTO;
import com.nutrireceitas.api.dto.ReceitaRequestDTO;
import com.nutrireceitas.api.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReceitaController {

    private final ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> listarTodas() {
        return ResponseEntity.ok(receitaService.listarTodas());
    }

    @GetMapping("/publicas")
    public ResponseEntity<List<ReceitaDTO>> listarPublicas() {
        return ResponseEntity.ok(receitaService.listarPublicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ReceitaDTO> criar(@Valid @RequestBody ReceitaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReceitaRequestDTO dto) {
        return ResponseEntity.ok(receitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        receitaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
