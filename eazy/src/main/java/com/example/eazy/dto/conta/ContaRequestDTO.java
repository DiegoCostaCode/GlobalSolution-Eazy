package com.example.eazy.dto.conta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public record ContaRequestDTO
        (
                @Positive(message = "O valor deve ser positivo")
                @NotNull(message = "O valor não pode ser nulo")
                double kwh,
                @Positive(message = "O valor deve ser positivo")
                @NotNull(message = "O valor não pode ser nulo")
                double valor,
                @DateTimeFormat(pattern = "MM-yyyy")
                @PastOrPresent(message = "A data não pode ser futura")
                @NotNull(message = "A data não pode ser nula")
                Date data
        ) {
}
