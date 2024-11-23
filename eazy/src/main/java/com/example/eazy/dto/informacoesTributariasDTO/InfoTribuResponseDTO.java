package com.example.eazy.dto.informacoesTributariasDTO;

import com.example.eazy.model.Estado;

import java.util.Date;

public record InfoTribuResponseDTO
        (
                long id,
                Estado estado,
                double valorKwh,
                Date ultimaAtualizacao
        ){
}
