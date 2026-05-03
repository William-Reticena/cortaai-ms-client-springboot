package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.request.UpdateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.dtos.response.ListBarbershopResponse;
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

    public BarbershopModel getBarbershopByOwnerId(Long id) {
        return barbershopRepository.findByOwnerId(id)
                .orElseThrow(() -> new DomainException(
                        "Barbearia não encontrada",
                        "Barbershop not found for id: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    public BarbershopModel getBarbershopByOwnerIdOrNull(Long id) {
        return barbershopRepository.findByOwnerId(id).orElse(null);
    }

    public List<BarbershopModel> listBarbershops() {
        return barbershopRepository.findAll();
    }

    public CreateBarbershopResponse getBarbershopDetails(Long id) {
        BarbershopModel barbershop = getBarbershopById(id);

        return BarbershopMapper.toCreateBarbershopResponse(barbershop);
    }

    public BarbershopModel createBarbershop(UserModel user, CreateBarbershopRequest request) {
        BarbershopModel barbershopAlreadyExists = barbershopRepository.findByOwnerId(user.getId()).orElse(null);

        if (barbershopAlreadyExists != null) {
            throw new DomainException(
                    "Barbearia já cadastrada para este usuário",
                    "Barbershop already exists for user id: " + user.getId(),
                    HttpStatus.BAD_REQUEST
            );
        }

        return barbershopRepository.save(BarbershopMapper.toBarbershopModel(user, request));
    }

    public BarbershopModel updateBarbershop(UserModel user, UpdateBarbershopRequest request) {
        BarbershopModel barbershop = getBarbershopByOwnerId(user.getId());

        return barbershopRepository.save(BarbershopMapper.toUpdatedBarbershopModel(barbershop, request));
    }
}
