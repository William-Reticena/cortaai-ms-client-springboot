package br.com.cortaai.client.dtos.feign.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterFeignRequest(

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+\\-]+@cortaai\\.com\\.br$",
                message = "Email must be a valid @cortaai.com.br address"
        )
        String dsEmail,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String dsPassword,

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String nmUser
) {
}
