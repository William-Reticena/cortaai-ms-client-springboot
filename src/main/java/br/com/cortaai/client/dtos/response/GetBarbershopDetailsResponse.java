package br.com.cortaai.client.dtos.response;

import br.com.cortaai.client.dtos.shared.EmployeeWithSpecialties;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
public record GetBarbershopDetailsResponse(
        BarbershopDetails barbershopDetails,
        List<OfferService> offerServices,
        List<EmployeeWithSpecialties> barbers

) {
    @Builder
    public record BarbershopDetails(
            String nmBarbershop,
            String dsAddress,
            String dsPhone,
            Boolean inOpen,
            LocalTime hrClosesAt,
            LocalDateTime dtNextAvailableSchedule
    ) {}

    @Builder
    public record OfferService(
            Long id,
            String nmService,
            String dsService,
            BigDecimal vlPrice,
            Integer nrDurationMinutes
    ) {}
}
