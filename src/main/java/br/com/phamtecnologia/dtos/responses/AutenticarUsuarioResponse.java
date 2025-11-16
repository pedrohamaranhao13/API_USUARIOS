package br.com.phamtecnologia.dtos.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record AutenticarUsuarioResponse(
        UUID id,
        String nome,
        String email,
        String perfil,
        LocalDateTime dataHoraCriacao,
        String accessToken
) {
}
