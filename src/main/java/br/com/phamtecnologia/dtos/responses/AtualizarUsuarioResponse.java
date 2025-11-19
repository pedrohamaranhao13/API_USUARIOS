package br.com.phamtecnologia.dtos.responses;

public record AtualizarUsuarioResponse(
        String nome,
        String telefone,
        String email
) {
}
