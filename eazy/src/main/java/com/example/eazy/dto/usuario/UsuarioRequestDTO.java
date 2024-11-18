package com.example.eazy.dto.usuario;

import com.example.eazy.model.Enum_estado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;


public record UsuarioRequestDTO(
        @Length(min = 3, max = 20, message = "O nome deve ter entre 3 e 20 caracteres")
        @NotBlank
        String usuario,
        @NotBlank
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message="E-mail inválido")
        String email,
        @NotBlank
        @Pattern(regexp = "^\\(?[0]?[1-9]{2}\\)?[\\s-]?[9]?[1-9]\\d{3}[-\\s]?\\d{4}", message = "Insira um telefone válido!")
        String telefone,
        @Length(min = 6, max = 20)
        @NotBlank
        String senha,
        @NotNull(message = "Estado não pode ser vazio. Informe seu estado.")
        Enum_estado estado
){}