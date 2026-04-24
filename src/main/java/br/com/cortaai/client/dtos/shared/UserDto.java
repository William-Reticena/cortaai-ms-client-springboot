package br.com.cortaai.client.dtos.shared;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String dsEmail,
        String nmUser
) {
}
