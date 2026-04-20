package com.nutrireceitas.api.repository;

import com.nutrireceitas.api.enums.StatusReceita;
import com.nutrireceitas.api.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByStatus(StatusReceita status);
}
