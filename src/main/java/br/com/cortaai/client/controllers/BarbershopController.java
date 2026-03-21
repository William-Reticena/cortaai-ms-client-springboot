package br.com.cortaai.client.controllers;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.facades.BarbershopFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("barbershops")
@RequiredArgsConstructor
public class BarbershopController {

    private final BarbershopFacade barbershopFacade;

    @PostMapping
    public ResponseEntity<CreateBarbershopResponse> createBarbershop(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CreateBarbershopRequest request
    ) {
        return ResponseEntity.ok(barbershopFacade.createBarbershop(token, request));
    }

    @GetMapping
    public ResponseEntity<List<String>> listBarbershops() {
        return ResponseEntity.ok(barbershopFacade.listBarbershops());
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getBarbershopDetails(@PathVariable String id) {

        return ResponseEntity.ok("Details for Barbershop with ID: " + id);
    }
}
