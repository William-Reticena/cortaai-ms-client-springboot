package br.com.cortaai.client.controllers;

import br.com.cortaai.client.dtos.request.CreateOfferRequest;
import br.com.cortaai.client.facades.OfferFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferFacade offerFacade;

    @PostMapping
    public ResponseEntity<Object> createUser(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CreateOfferRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(offerFacade.createOffer(token, request));
    }
}
