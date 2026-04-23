package br.com.cortaai.client.controllers;

import br.com.cortaai.client.dtos.request.AuthRequest;
import br.com.cortaai.client.dtos.request.RefreshTokenRequest;
import br.com.cortaai.client.dtos.response.AuthResponse;
import br.com.cortaai.client.facades.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authFacade.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(
            @RequestBody RefreshTokenRequest request) {
        AuthResponse response = authFacade.refreshToken(request.dsRefreshToken());
        return ResponseEntity.ok(response);
    }
}
