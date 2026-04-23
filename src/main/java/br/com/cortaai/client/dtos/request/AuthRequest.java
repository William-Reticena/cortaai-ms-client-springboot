package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.*;

public record AuthRequest(

        @NotNull(message = "dsEmail is required.")
        @Email(message = "dsEmail must be a valid email address.")
        String dsEmail,

        @NotNull(message = "dsPassword is required.")
        @Size(min = 8, message = "dsPassword must be at least 8 characters long.")
        String dsPassword
) {
}
