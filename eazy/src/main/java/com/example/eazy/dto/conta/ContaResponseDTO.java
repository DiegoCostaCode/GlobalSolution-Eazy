package com.example.eazy.dto.conta;

import java.sql.Date;

public record ContaResponseDTO
        (
                Long id,
                double kwh,
                double valor,
                Date data
        ){}
