package com.example.eazy.dto.informacoesTributariasDTO;

import com.example.eazy.model.Enum_estado;

import java.util.Date;

public record InfoTribuResponseDTO
        (
                long id,
                Enum_estado estado,
                double valorKwh,
                Date ultimaAtualizacao
        ){
}
