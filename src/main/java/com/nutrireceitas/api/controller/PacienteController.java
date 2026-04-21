package com.nutrireceitas.api.controller;

import com.nutrireceitas.api.dto.PacienteDTO;
import com.nutrireceitas.api.dto.PacienteRequestDTO;
import com.nutrireceitas.api.dto.RegistroPesoDTO;
import com.nutrireceitas.api.service.IPacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final IPacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> criar(@Valid @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.ok(pacienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pacienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/historico-peso")
    public ResponseEntity<List<RegistroPesoDTO>> listarHistoricoPeso(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.listarHistoricoPeso(id));
    }

    @PostMapping("/{id}/historico-peso")
    public ResponseEntity<PacienteDTO> adicionarRegistroPeso(
            @PathVariable Long id,
            @Valid @RequestBody RegistroPesoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pacienteService.adicionarRegistroPeso(id, dto));
    }

    @DeleteMapping("/{id}/historico-peso/{registroId}")
    public ResponseEntity<Void> excluirRegistroPeso(
            @PathVariable Long id,
            @PathVariable Long registroId) {
        pacienteService.excluirRegistroPeso(id, registroId);
        return ResponseEntity.noContent().build();
    }
}
