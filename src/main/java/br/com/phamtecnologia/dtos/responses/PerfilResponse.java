package br.com.phamtecnologia.dtos.responses;

import java.util.UUID;

public record PerfilResponse(
        UUID id,
        String nome
) {
}
