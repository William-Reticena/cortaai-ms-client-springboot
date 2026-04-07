package br.com.cortaai.client.dtos.request;

import br.com.cortaai.client.interfaces.HasOpenCloseTime;
import br.com.cortaai.client.validators.annotations.OpenBeforeClose;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@OpenBeforeClose
public record UpdateBarbershopRequest(

        @NotBlank(message = "nmBarbershop is required")
        @Size(min = 3, max = 50, message = "nmBarbershop must be between 3 and 50 characters")
        String nmBarbershop,

        @NotBlank(message = "dsAddress is required")
        @Size(min = 10, max = 255, message = "dsAddress must be between 10 and 255 characters")
        String dsAddress,

        @NotBlank(message = "dsPhone is required")
        @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "dsPhone must be a valid phone number")
        String dsPhone,

        @NotNull(message = "hrOpensAt is required")
        LocalTime hrOpensAt,

        @NotNull(message = "hrClosesAt is required")
        LocalTime hrClosesAt,

        @NotNull(message = "inOpen is required")
        Boolean inOpen
) implements HasOpenCloseTime {
}
