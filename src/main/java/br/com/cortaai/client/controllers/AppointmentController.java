package br.com.cortaai.client.controllers;

import br.com.cortaai.client.dtos.request.CreateAppointmentRequest;
import br.com.cortaai.client.dtos.response.CreateAppointmentResponse;
import br.com.cortaai.client.dtos.shared.UserDto;
import br.com.cortaai.client.facades.AppointmentFacade;
import br.com.cortaai.client.validators.annotations.AuthenticatedUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentFacade appointmentFacade;

    @PostMapping
    public ResponseEntity<CreateAppointmentResponse> createAppointment(
            @AuthenticatedUser UserDto userAuthenticated,
            @RequestBody @Valid CreateAppointmentRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentFacade.createAppointment(userAuthenticated, request));
    }
}
