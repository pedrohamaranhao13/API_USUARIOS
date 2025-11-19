package br.com.phamtecnologia.dtos.responses;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String telefone,
        String email,
        String Perfil
) {
}
