package com.example.eazy.dto.recompensas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record RecompensasRequestDTO
        (
                @Length(min = 10, message = "O valor deve ter no mínimo 10 digitos e no máximo")
                @NotBlank
                String nome,
                @NotBlank
                @Length(min = 10, max = 20)
                String descricao,
                @Positive(message = "O valor da recompensa deve ser positivo")
                @NotBlank(message = "Não pode ser vazio")
                double valor
        ) {
}
