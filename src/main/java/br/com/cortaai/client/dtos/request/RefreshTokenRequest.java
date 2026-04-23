package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(

        @NotBlank(message = "dsRefreshToken is required.")
        String dsRefreshToken
) {
}
