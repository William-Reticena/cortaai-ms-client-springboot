package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.request.CreateOfferRequest;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.ServiceModel;

public class OfferMapper {

    public static ServiceModel toModel(BarbershopModel barbershop, CreateOfferRequest request) {
        return ServiceModel.builder()
                .barbershop(barbershop)
                .nmService(request.nmService())
                .dsService(request.dsService())
                .vlPrice(request.vlPrice())
                .nrDurationMinutes(request.nrDurationMinutes())
                .inActive(Boolean.FALSE)
                .build();
    }
}
