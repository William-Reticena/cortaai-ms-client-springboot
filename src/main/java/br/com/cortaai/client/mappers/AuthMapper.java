package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.feign.response.AuthFeignResponse;
import br.com.cortaai.client.dtos.response.AuthResponse;

public class AuthMapper {

    public static AuthResponse toAuthResponse(AuthFeignResponse feignResponse) {
        return br.com.cortaai.client.dtos.response.AuthResponse.builder()
                .dsAccessToken(feignResponse.dsAccessToken())
                .dsRefreshToken(feignResponse.dsRefreshToken())
                .dsTokenType(feignResponse.dsTokenType())
                .nrExpiresIn(feignResponse.nrExpiresIn())
                .build();
    }

    public static AuthResponse toAuthResponse(String accessToken, String refreshToken, String tokenType, Integer expiresIn) {
        return br.com.cortaai.client.dtos.response.AuthResponse.builder()
                .dsAccessToken(accessToken)
                .dsRefreshToken(refreshToken)
                .dsTokenType(tokenType)
                .nrExpiresIn(expiresIn)
                .build();
    }
}
