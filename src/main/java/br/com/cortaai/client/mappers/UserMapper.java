package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.feign.response.RegisterFeignResponse;
import br.com.cortaai.client.dtos.request.CreateBarberRequest;
import br.com.cortaai.client.dtos.request.CreateUserRequest;
import br.com.cortaai.client.dtos.response.CreateUserResponse;
import br.com.cortaai.client.dtos.response.MeResponse;
import br.com.cortaai.client.enums.UserRoleEnum;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;

public class UserMapper {

    public static UserModel toUserModel(CreateUserRequest request, RegisterFeignResponse feignResponse) {
        return UserModel.builder()
                .id(feignResponse.user().id())
                .nmUser(request.nmUser())
                .dsPhone(request.dsPhone())
                .dsEmail(request.dsEmail())
                .dsPassword(request.dsPassword())
                .tpRole(UserRoleEnum.valueOf(request.tpRole()))
                .build();
    }

    public static UserModel toUserModel(CreateBarberRequest request) {
        return UserModel.builder()
                .nmUser(request.nmUser())
                .dsPhone(request.dsPhone())
                .tpRole(UserRoleEnum.BARBER)
                .build();
    }

    public static CreateUserResponse toCreateUserResponse(UserModel model) {
        return CreateUserResponse.builder()
                .nmUser(model.getNmUser())
                .dsPhone(model.getDsPhone())
                .dsEmail(model.getDsEmail())
                .build();
    }

    public static MeResponse toMeResponse(UserModel userModel, BarbershopModel barbershopModel) {
        return MeResponse.builder()
                .nmUser(userModel.getNmUser())
                .dsPhone(userModel.getDsPhone())
                .dsEmail(userModel.getDsEmail())
                .tpRole(userModel.getTpRole().getCode())
                .idBarbershop(barbershopModel != null ? barbershopModel.getId() : null)
                .build();
    }
}
