package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.AuthRequest;
import br.com.cortaai.client.dtos.request.RefreshTokenRequest;
import br.com.cortaai.client.dtos.feign.response.AuthFeignResponse;
import br.com.cortaai.client.feign.AuthServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthServiceClient authServiceClient;

    public AuthFeignResponse authenticate(AuthRequest request) {
        return authServiceClient.login(request);
    }

    public AuthFeignResponse refreshToken(String refreshToken) {
        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken);
        return authServiceClient.refresh(request);
    }
}
