package br.com.cortaai.client.dtos.feign.response;

import br.com.cortaai.client.dtos.shared.UserDto;
import lombok.Builder;

@Builder
public record ValidateFeignResponse(

    Boolean inValid,
    UserDto user
) {
}
