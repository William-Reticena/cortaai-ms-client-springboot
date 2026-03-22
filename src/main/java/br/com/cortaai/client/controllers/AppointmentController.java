package br.com.cortaai.client.controllers;

import br.com.cortaai.client.dtos.request.CreateAppointmentRequest;
import br.com.cortaai.client.dtos.response.CreateAppointmentResponse;
import br.com.cortaai.client.facades.AppointmentFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentFacade appointmentFacade;

    @PostMapping
    public ResponseEntity<CreateAppointmentResponse> createAppointment(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CreateAppointmentRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentFacade.createAppointment(token, request));
    }
}
