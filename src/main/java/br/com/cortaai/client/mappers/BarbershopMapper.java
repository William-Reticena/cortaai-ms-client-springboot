package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.dtos.response.ListBarbershopResponse;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class BarbershopMapper {

    public static BarbershopModel toBarbershopModel(UserModel user, CreateBarbershopRequest request) {
        return BarbershopModel.builder()
                .owner(user)
                .nmBarbershop(request.nmBarbershop())
                .build();
    }

    public static CreateBarbershopResponse toCreateBarbershopResponse(BarbershopModel model) {
        return CreateBarbershopResponse.builder()
                .nmBarbershop(model.getNmBarbershop())
                .build();
    }

    public static List<ListBarbershopResponse> toListBarbershopResponse(List<BarbershopModel> models) {
        return models.stream()
                .map(model -> ListBarbershopResponse.builder()
                        .id(model.getId())
                        .nmBarbershop(model.getNmBarbershop())
                        .dsAddress("Not Implemented")
                        .inOpen(Boolean.FALSE)
                        .hrClosesAt(LocalTime.now().plusMinutes(30))
                        .dtNextAvailableSchedule(LocalDateTime.now())
                        .build())
                .toList();
    }
}
