package br.com.phamtecnologia.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AutenticarUsuarioRequest(

        @Email(message = "Por favor, digite um e-mail válido.")
        @NotBlank(message = "Por favor, informe o e-mail do usuário.")
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.")
        @NotBlank(message = "Por favor, preencha a senha do usuário.")
        String senha
) {
}
