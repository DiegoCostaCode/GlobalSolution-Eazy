package com.example.eazy.repository;

import com.example.eazy.model.Estado;
import com.example.eazy.model.InformacoesTributarias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoTributariaRepository extends JpaRepository<InformacoesTributarias,Long> {

    Optional<InformacoesTributarias> findByEstado(Estado estado);
}
