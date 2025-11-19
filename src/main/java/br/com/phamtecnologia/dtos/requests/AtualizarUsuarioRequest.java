package br.com.phamtecnologia.dtos.requests;

public record AtualizarUsuarioRequest(
        String nome,
        String telefone,
        String email,
        String senha
) {
}
