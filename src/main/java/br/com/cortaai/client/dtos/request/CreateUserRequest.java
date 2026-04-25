package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.*;

public record CreateUserRequest(

        @NotBlank(message = "nmUser is required.")
        String nmUser,

        @NotBlank(message = "dsPhone is required.")
        String dsPhone,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+\\-]+@cortaai\\.com\\.br$",
                message = "Email must be a valid @cortaai.com.br address"
        )
        String dsEmail,

        @NotBlank(message = "dsPassword is required.")
        String dsPassword,

        @Min(value = 1, message = "tpRole must be a valid role identifier.")
        @Max(value = 3, message = "tpRole must be a valid role identifier.")
        Integer tpRole
) {
}
