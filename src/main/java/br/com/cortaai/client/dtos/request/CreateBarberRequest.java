package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateBarberRequest(

        @NotBlank(message = "nmUser is required")
        String nmUser,

        @NotBlank(message = "dsPhone is required")
        String dsPhone,

        @NotEmpty(message = "specialties must have at least one item")
        List<@NotBlank(message = "specialty cannot be blank") String> specialties
) {
}
