package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record ValidateTokenRequest(

        @NotBlank(message = "dsToken is required.")
        String dsToken
) {
}
