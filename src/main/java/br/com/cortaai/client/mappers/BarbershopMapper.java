package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.dtos.response.CreateUserResponse;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;

public class BarbershopMapper {

    public static BarbershopModel toModel(UserModel user, CreateBarbershopRequest request) {
        return BarbershopModel.builder()
                .owner(user)
                .nmBarbershop(request.nmBarbershop())
                .build();
    }

    public static CreateBarbershopResponse toResponse(BarbershopModel model) {
        return CreateBarbershopResponse.builder()
                .nmBarbershop(model.getNmBarbershop())
                .build();
    }
}
