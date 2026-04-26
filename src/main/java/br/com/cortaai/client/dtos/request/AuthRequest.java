package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.*;

public record AuthRequest(

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+\\-]+@cortaai\\.com\\.br$",
                message = "Email must be a valid @cortaai.com.br address"
        )
        String dsEmail,

        @NotNull(message = "dsPassword is required.")
        @Size(min = 6, message = "dsPassword must be at least 6 characters long.")
        String dsPassword
) {
}
