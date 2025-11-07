package br.com.phamtecnologia.dtos.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record CriarUsuarioResponse(
        UUID id,
        String nome,
        String telefone,
        String email,
        String perfil,
        LocalDateTime dataHoraCriacao
) {
}
