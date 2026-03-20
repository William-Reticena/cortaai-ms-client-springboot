package br.com.cortaai.client.controllers;

import br.com.cortaai.client.facades.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<List<String>> createUser(){
        return ResponseEntity.ok(userFacade.createUser());
    }
}
