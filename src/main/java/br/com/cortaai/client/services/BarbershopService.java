package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.mappers.BarbershopMapper;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.repositories.BarbershopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbershopService {

    private final BarbershopRepository barbershopRepository;

    public List<String> listBarbershops() {
        // This is a placeholder implementation. In a real application, this would likely
        // involve fetching data from a database or an external API.
        return List.of("Barbershop A", "Barbershop B", "Barbershop C");
    }

    public CreateBarbershopResponse createBarbershop(UserModel user, CreateBarbershopRequest request) {
        BarbershopModel barbershop = barbershopRepository.save(BarbershopMapper.toModel(user, request));

        return BarbershopMapper.toResponse(barbershop);
    }
}
