package com.nutrireceitas.api.repository;

import com.nutrireceitas.api.model.RegistroPeso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPesoRepository extends JpaRepository<RegistroPeso, Long> {
    List<RegistroPeso> findByPacienteIdOrderByDataAsc(Long pacienteId);
}
