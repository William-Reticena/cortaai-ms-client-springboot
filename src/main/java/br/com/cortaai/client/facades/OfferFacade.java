package br.com.cortaai.client.facades;

import br.com.cortaai.client.dtos.request.CreateOfferRequest;
import br.com.cortaai.client.dtos.request.CreateUserRequest;
import br.com.cortaai.client.dtos.response.CreateUserResponse;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.services.BarbershopService;
import br.com.cortaai.client.services.OfferService;
import br.com.cortaai.client.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferFacade {

    private final BarbershopService barbershopService;
    private final OfferService offerService;

    public Object createOffer(String token, CreateOfferRequest request) {
        BarbershopModel barbershop = barbershopService.getBarbershopByOwnerId(Long.valueOf(token));
        return offerService.createOffer(barbershop, request);
    }
}
