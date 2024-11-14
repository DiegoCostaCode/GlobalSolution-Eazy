package com.example.eazy.repository;

import com.example.eazy.model.InformacoesTributarias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface InfoTributariaRepository extends JpaRepository<InformacoesTributarias,Long> {
}
