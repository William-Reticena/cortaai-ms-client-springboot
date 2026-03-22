package br.com.cortaai.client.dtos.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record ListBarbershopResponse(

        Long id,
        String nmBarbershop,
        String dsAddress,
        Boolean inOpen,
        LocalTime hrClosesAt,
        LocalDateTime dtNextAvailableSchedule
) {
}
