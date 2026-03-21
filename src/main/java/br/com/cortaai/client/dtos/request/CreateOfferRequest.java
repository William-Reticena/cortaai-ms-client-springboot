package br.com.cortaai.client.dtos.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateOfferRequest(

        @NotBlank(message = "nmService is required")
        String nmService,

        String dsService,

        @NotNull(message = "vlPrice is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "vlPrice must be greater than 0")
        @Digits(integer = 4, fraction = 2, message = "vlPrice must be a valid monetary amount")
        BigDecimal vlPrice,

        @NotNull(message = "nrDurationMinutes is required")
        @Positive(message = "nrDurationMinutes must be a positive integer")
        @Max(value = 240, message = "nrDurationMinutes must be less than or equal to 240")
        Integer nrDurationMinutes
) {
}
