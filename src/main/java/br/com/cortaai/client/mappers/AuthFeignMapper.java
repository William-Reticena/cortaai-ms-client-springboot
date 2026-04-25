package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.feign.request.RegisterFeignRequest;
import br.com.cortaai.client.dtos.request.CreateUserRequest;

public class AuthFeignMapper {

    public static RegisterFeignRequest toRegisterRequest(CreateUserRequest request) {
        return new RegisterFeignRequest(request.dsEmail(), request.dsPassword(), request.nmUser());
    }
}
