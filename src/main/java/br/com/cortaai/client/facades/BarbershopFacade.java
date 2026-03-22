package br.com.cortaai.client.facades;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.dtos.response.ListBarbershopResponse;
import br.com.cortaai.client.mappers.BarbershopMapper;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.services.BarbershopService;
import br.com.cortaai.client.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BarbershopFacade {

    private final BarbershopService barbershopService;
    private final UserService userService;

    public CreateBarbershopResponse createBarbershop(String token, CreateBarbershopRequest request) {
        UserModel user = userService.getOwner(Long.valueOf(token));

        return barbershopService.createBarbershop(user, request);
    }

    public List<ListBarbershopResponse> listBarbershops() {
        List<BarbershopModel> barbershops = barbershopService.listBarbershops();

        return BarbershopMapper.toListBarbershopResponse(barbershops);
    }

    public CreateBarbershopResponse getBarbershopDetails(Long id) {
        return barbershopService.getBarbershopDetails(id);
    }
}
