package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.feign.request.RegisterFeignRequest;
import br.com.cortaai.client.dtos.feign.response.AuthFeignResponse;
import br.com.cortaai.client.dtos.feign.response.RegisterFeignResponse;
import br.com.cortaai.client.dtos.request.AuthRequest;
import br.com.cortaai.client.dtos.request.RefreshTokenRequest;
import br.com.cortaai.client.dtos.feign.request.ValidateTokenRequest;
import br.com.cortaai.client.dtos.feign.response.ValidateFeignResponse;
import br.com.cortaai.client.feign.AuthServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthServiceClient authServiceClient;

    public RegisterFeignResponse register(RegisterFeignRequest request) {
        return authServiceClient.register(request);
    }

    public AuthFeignResponse authenticate(AuthRequest request) {
        return authServiceClient.login(request);
    }

    public AuthFeignResponse refreshToken(String refreshToken) {
        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken);
        return authServiceClient.refresh(request);
    }

    public ValidateFeignResponse validateToken(String dsToken) {
        return authServiceClient.validateToken(new ValidateTokenRequest(dsToken));
    }
}
