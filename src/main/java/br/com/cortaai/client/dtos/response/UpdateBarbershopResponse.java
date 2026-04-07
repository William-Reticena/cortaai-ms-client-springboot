package br.com.cortaai.client.dtos.response;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record UpdateBarbershopResponse(
        String nmBarbershop,
        String dsAddress,
        String dsPhone,
        LocalTime hrOpensAt,
        LocalTime hrClosesAt,
        Boolean inOpen
) {
}
