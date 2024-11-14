package com.example.eazy.dto.informacoesTributariasDTO;

import com.example.eazy.model.Enum_estado;
import com.example.eazy.model.InformacoesTributarias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record InfoTributariasRequestDTO
        (
                @NotBlank(message = "Estado não pode ser vazio")
                Enum_estado estado,
                @Positive
                @NotBlank(message = "Valor em KW/h não pode ser vazio")
                double valorKwh
        ) {
}
