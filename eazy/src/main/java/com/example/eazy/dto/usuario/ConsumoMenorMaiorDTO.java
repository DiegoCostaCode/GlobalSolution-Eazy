package com.example.eazy.dto.usuario;

import javax.xml.crypto.Data;
import java.sql.Date;

public record ConsumoMenorMaiorDTO
        (
                Double gasto,
                Date data
        ) {
}
