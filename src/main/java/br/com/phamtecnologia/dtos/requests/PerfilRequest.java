package br.com.phamtecnologia.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PerfilRequest(

        @Size(min = 3, max = 150, message = "Nome deve conter de 3 a 150 caracteres.")
        @NotBlank(message = "Por favor, preencha o nome do usuário.")
        String nome
) {
}
