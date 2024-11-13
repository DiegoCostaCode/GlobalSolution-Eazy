package com.example.eazy.dto.recompensas;

public record RecompensasResposeDTO
        (
                Long id,
                String nome,
                String descricao,
                double valor
        ) {
}
