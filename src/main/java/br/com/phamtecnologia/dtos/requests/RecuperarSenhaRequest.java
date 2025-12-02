package br.com.phamtecnologia.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RecuperarSenhaRequest(

        @Email(message = "Por favor, digite um e-mail válido.")
        @NotBlank(message = "Por favor, informe o e-mail do usuário.")
        String email
) {
}
