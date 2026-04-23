package br.com.cortaai.client.feign;

import br.com.cortaai.client.dtos.request.AuthRequest;
import br.com.cortaai.client.dtos.request.RefreshTokenRequest;
import br.com.cortaai.client.dtos.feign.response.AuthFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "authServiceClient",
    url = "${auth-service.url}",
    configuration = br.com.cortaai.client.configs.FeignLoggerConfig.class
)
public interface AuthServiceClient {

    @PostMapping("/auth/login")
    AuthFeignResponse login(@RequestBody AuthRequest request);

    @PostMapping("/auth/refresh")
    AuthFeignResponse refresh(@RequestBody RefreshTokenRequest request);
}
