package br.com.cortaai.client.services;

import br.com.cortaai.client.exceptions.DomainException;
import br.com.cortaai.client.models.*;
import br.com.cortaai.client.repositories.AppointmentRepository;
import br.com.cortaai.client.repositories.AppointmentServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentServiceRepository appointmentServiceRepository;

    public AppointmentModel createAppointment(AppointmentModel model) {
        return appointmentRepository.save(model);
    }

    public void attachToService(AppointmentServiceModel model) {
        appointmentServiceRepository.save(model);
    }

    public void verifyIfScheduleAvailable(Long idBarber, LocalDateTime dtAppointment, Integer serviceDurationMinutes) {
        LocalTime hrStart = dtAppointment.toLocalTime();
        LocalTime hrEnd = hrStart.plusMinutes(serviceDurationMinutes);

        Boolean isOccupied = appointmentRepository.existsOverlappingAppointment(idBarber, dtAppointment.toLocalDate(), hrStart, hrEnd);

        if (isOccupied) {
            throw new DomainException(
                    "O barbeiro selecionado não está disponível no horário escolhido. Por favor, escolha outro horário ou barbeiro.",
                    "Barber Unavailable",
                    HttpStatus.CONFLICT
            );
        }
    }
}
