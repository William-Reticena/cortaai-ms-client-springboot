package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.request.CreateUserRequest;
import br.com.cortaai.client.dtos.response.CreateUserResponse;
import br.com.cortaai.client.enums.UserRoleEnum;
import br.com.cortaai.client.models.UserModel;

public class UserMapper {

    public static UserModel toModel(CreateUserRequest request) {
        return UserModel.builder()
                .nmUser(request.nmUser())
                .dsPhone(request.dsPhone())
                .dsPassword(request.dsPassword())
                .tpRole(UserRoleEnum.valueOf(request.tpRole()))
                .build();
    }

    public static CreateUserResponse toResponse(UserModel model) {
        return CreateUserResponse.builder()
                .nmUser(model.getNmUser())
                .dsPhone(model.getDsPhone())
                .build();
    }
}
