package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CreateAppointmentRequest(

        @NotNull(message = "idBarber is required.")
        @Positive(message = "idBarber must be a positive number.")
        Long idBarber,

        @NotNull(message = "idService is required.")
        @Positive(message = "idService must be a positive number.")
        Long idService,

        @NotNull(message = "dtAppointment is required.")
        @Future(message = "dtAppointment must be a future date and time.")
        LocalDateTime dtAppointment,

        @Size(max = 255, message = "dsNotes must not exceed 255 characters.")
        String dsNotes
) {
}
