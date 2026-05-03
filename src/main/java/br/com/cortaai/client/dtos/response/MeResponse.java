package br.com.cortaai.client.dtos.response;

import lombok.Builder;

@Builder
public record MeResponse(
        String nmUser,
        String dsPhone,
        String dsEmail,
        Integer tpRole,
        Long idBarbershop
) {
}
