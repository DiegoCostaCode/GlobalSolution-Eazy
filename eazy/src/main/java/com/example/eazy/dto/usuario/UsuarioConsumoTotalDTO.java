package com.example.eazy.dto.usuario;

import java.util.Date;

public record UsuarioConsumoTotalDTO
        (
                Double TotalKwhConsumo,
                Double ValorGastoEmReais,
                Date DesDe,
                Date Ate
        ) {
}
