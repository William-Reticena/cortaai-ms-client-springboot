package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

        @NotBlank(message = "nmUser is required.")
        String nmUser,

        @NotBlank(message = "dsPhone is required.")
        String dsPhone,

        @NotBlank(message = "dsPassword is required.")
        String dsPassword,

        @Min(value = 1, message = "tpRole must be a valid role identifier.")
        @Max(value = 3, message = "tpRole must be a valid role identifier.")
        Integer tpRole
) {
}
