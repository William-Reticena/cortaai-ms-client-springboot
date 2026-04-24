package br.com.cortaai.client.facades;

import br.com.cortaai.client.configs.AppLogger;
import br.com.cortaai.client.dtos.feign.response.AuthFeignResponse;
import br.com.cortaai.client.dtos.feign.response.ValidateFeignResponse;
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
    private static final AppLogger log = AppLogger.getLogger(AuthFacade.class);

    public AuthResponse login(AuthRequest request) {
        AuthFeignResponse feignResponse = authService.authenticate(request);
        return AuthMapper.toAuthResponse(feignResponse);
    }

    public AuthResponse refreshToken(String refreshToken) {
        var goAuthResponse = authService.refreshToken(refreshToken);
        return AuthMapper.toAuthResponse(goAuthResponse);
    }

    public Boolean validateToken(String dsToken) {
        try {
            ValidateFeignResponse feignResponse = authService.validateToken(dsToken);

            if (feignResponse == null) {
                log.error("Token validation returned null response");
                return false;
            }
            
            return feignResponse.inValid();
        } catch (Exception ex) {
            log.error("Error in validateToken: " + ex.getMessage(), ex);
            return false;
        }
    }
}
