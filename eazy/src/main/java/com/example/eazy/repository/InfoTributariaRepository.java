package com.example.eazy.repository;

import com.example.eazy.model.Enum_estado;
import com.example.eazy.model.InformacoesTributarias;
import com.example.eazy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface InfoTributariaRepository extends JpaRepository<InformacoesTributarias,Long> {

    Optional<InformacoesTributarias> findByEstado(Enum_estado estado);
}
