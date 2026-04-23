package br.com.cortaai.client.dtos.feign.response;

import lombok.Builder;

@Builder
public record AuthFeignResponse(

        String dsAccessToken,
        String dsRefreshToken,
        String dsTokenType,
        Integer nrExpiresIn
) {
}
