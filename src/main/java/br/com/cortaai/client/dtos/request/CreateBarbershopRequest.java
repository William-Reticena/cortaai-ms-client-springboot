package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CreateBarbershopRequest(

        @NotBlank(message = "nmBarbershop is required")
        String nmBarbershop
) {
}
