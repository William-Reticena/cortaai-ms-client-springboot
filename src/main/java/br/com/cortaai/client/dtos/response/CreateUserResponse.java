package br.com.cortaai.client.dtos.response;

import lombok.Builder;

@Builder
public record CreateUserResponse (
        String nmUser,
        String dsPhone
) {
}
