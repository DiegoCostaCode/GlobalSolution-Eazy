package com.example.eazy.repository;

import com.example.eazy.model.Recompensas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RecompensasRepository extends JpaRepository<Recompensas,Long> {
}
