package br.com.phamtecnologia.dtos.requests;

public record CriarUsuarioRequest(
        String nome,
        String telefone,
        String email,
        String senha,
        String perfil
) {
}
