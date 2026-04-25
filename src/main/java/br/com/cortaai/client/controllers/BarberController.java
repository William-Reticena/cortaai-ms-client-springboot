package br.com.cortaai.client.controllers;

import br.com.cortaai.client.dtos.request.CreateBarberRequest;
import br.com.cortaai.client.facades.BarberFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/barbers")
@RequiredArgsConstructor
public class BarberController {

    private final BarberFacade barberFacade;

    @PostMapping
    public ResponseEntity<CreateBarberRequest> createBarber(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CreateBarberRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(barberFacade.createBarber(token, request));
    }
}
