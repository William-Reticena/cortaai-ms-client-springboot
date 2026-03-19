package br.com.cortaai.client.controllers;

import br.com.cortaai.client.facades.BarbershopFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("barbershops")
@RequiredArgsConstructor
public class BarbershopController {

    private final BarbershopFacade barbershopFacade;

    @GetMapping
    public ResponseEntity<List<String>> listBarbershops() {
        return ResponseEntity.ok(barbershopFacade.listBarbershops());
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getBarbershopDetails(@PathVariable String id) {

        return ResponseEntity.ok("Details for Barbershop with ID: " + id);
    }
}
