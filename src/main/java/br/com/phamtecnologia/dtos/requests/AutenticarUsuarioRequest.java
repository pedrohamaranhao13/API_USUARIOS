package br.com.phamtecnologia.dtos.requests;

public record AutenticarUsuarioRequest(
        String email,
        String senha
) {
}
