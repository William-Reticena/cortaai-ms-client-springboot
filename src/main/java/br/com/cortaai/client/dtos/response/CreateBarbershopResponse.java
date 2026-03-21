package br.com.cortaai.client.dtos.response;

import lombok.Builder;

@Builder
public record CreateBarbershopResponse(
        String nmBarbershop
) {
}
