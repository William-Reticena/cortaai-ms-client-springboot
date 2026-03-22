package br.com.cortaai.client.dtos.response;

import br.com.cortaai.client.enums.AppointmentStatusEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record CreateAppointmentResponse(
        String nmBarbershop,
        String nmBarber,
        LocalDate dtAppointment,
        LocalTime hrStart,
        LocalTime hrEnd,
        String dsNotes,
        AppointmentStatusEnum tpStatus
) {
}
