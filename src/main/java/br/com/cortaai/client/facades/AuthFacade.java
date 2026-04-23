package br.com.cortaai.client.facades;

import br.com.cortaai.client.dtos.feign.response.AuthFeignResponse;
import br.com.cortaai.client.dtos.request.AuthRequest;
import br.com.cortaai.client.dtos.response.AuthResponse;
import br.com.cortaai.client.mappers.AuthMapper;
import br.com.cortaai.client.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService authService;

    public AuthResponse login(AuthRequest request) {
        AuthFeignResponse feignResponse = authService.authenticate(request);
        return AuthMapper.toAuthResponse(feignResponse);
    }

    public AuthResponse refreshToken(String refreshToken) {
        var goAuthResponse = authService.refreshToken(refreshToken);
        return AuthMapper.toAuthResponse(goAuthResponse);
    }
}
