package br.com.phamtecnologia.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequest(

        @Size(min = 3, max = 150, message = "Nome deve conter de 3 a 150 caracteres.")
        @NotBlank(message = "Por favor, preencha o nome do usuário.")
        String nome,

        @Size(min = 11, max = 15, message = "Telefone deve conter de 11 a 15 caracteres.")
        String telefone,

        @Email(message = "Por favor, digite um e-mail válido.")
        @NotBlank(message = "Por favor, informe o e-mail do usuário.")
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.")
        @NotBlank(message = "Por favor, preencha a senha do usuário.")
        String senha,

        @NotBlank(message = "Por favor, preencha um perfil.")
        String perfil
) {
}
