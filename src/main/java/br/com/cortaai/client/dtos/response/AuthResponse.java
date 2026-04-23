package br.com.cortaai.client.dtos.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String dsAccessToken,
        String dsRefreshToken,
        String dsTokenType,
        Integer nrExpiresIn
) {
}
