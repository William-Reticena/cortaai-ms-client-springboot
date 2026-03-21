package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.exceptions.DomainException;
import br.com.cortaai.client.mappers.BarbershopMapper;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.repositories.BarbershopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbershopService {

    private final BarbershopRepository barbershopRepository;

    public BarbershopModel getBarbershopById(Long id) {
        return barbershopRepository.findById(id)
                .orElseThrow(() -> new DomainException(
                        "Barbearia não encontrada",
                        "Barbershop not found for id: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    public List<CreateBarbershopResponse> listBarbershops() {
        return barbershopRepository.findAll().stream()
                .map(BarbershopMapper::toResponse)
                .toList();
    }

    public CreateBarbershopResponse getBarbershopDetails(Long id) {
        BarbershopModel barbershop = barbershopRepository.findById(id)
                .orElseThrow(() -> new DomainException(
                        "Barbearia não encontrada",
                        "Barbershop not found for id: " + id,
                        HttpStatus.NOT_FOUND
                ));

        return BarbershopMapper.toResponse(barbershop);
    }

    public CreateBarbershopResponse createBarbershop(UserModel user, CreateBarbershopRequest request) {
        BarbershopModel barbershop = barbershopRepository.save(BarbershopMapper.toModel(user, request));

        return BarbershopMapper.toResponse(barbershop);
    }
}
