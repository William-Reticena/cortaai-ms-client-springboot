package br.com.cortaai.client.controllers;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.request.UpdateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.dtos.response.GetBarbershopDetailsResponse;
import br.com.cortaai.client.dtos.response.ListBarbershopResponse;
import br.com.cortaai.client.dtos.response.UpdateBarbershopResponse;
import br.com.cortaai.client.facades.BarbershopFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/barbershops")
@RequiredArgsConstructor
public class BarbershopController {

    private final BarbershopFacade barbershopFacade;

    @PostMapping
    public ResponseEntity<CreateBarbershopResponse> createBarbershop(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CreateBarbershopRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(barbershopFacade.createBarbershop(token, request));
    }

    @PutMapping
    public ResponseEntity<UpdateBarbershopResponse> updateBarbershop(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid UpdateBarbershopRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(barbershopFacade.updateBarbershop(token, request));
    }

    @GetMapping
    public ResponseEntity<List<ListBarbershopResponse>> listBarbershops() {
        return ResponseEntity.status(HttpStatus.OK).body(barbershopFacade.listBarbershops());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetBarbershopDetailsResponse> getBarbershopDetails(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(barbershopFacade.getBarbershopDetails(id));
    }
}
