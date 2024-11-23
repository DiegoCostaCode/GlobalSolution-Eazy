package com.example.eazy.dto.informacoesTributariasDTO;

import com.example.eazy.model.Estado;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InfoTribuRequestDTO
        (
                @NotNull(message = "Estado não pode ser vazio")
                Estado estado,
                @Positive
                @NotNull(message = "Valor em KW/h não pode ser vazio")
                double valorKwh
        ) {
}
