package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateOfferRequest;
import br.com.cortaai.client.exceptions.DomainException;
import br.com.cortaai.client.mappers.OfferMapper;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.ServiceModel;
import br.com.cortaai.client.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public ServiceModel createOffer(BarbershopModel barbershop, CreateOfferRequest request) {
        return offerRepository.save(OfferMapper.toModel(barbershop, request));
    }

    public ServiceModel getOfferServiceById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new DomainException(
                        "Serviço não encontrado",
                        "Service not found for id: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    public List<ServiceModel> listOfferServicesByBarbershopId(Long barbershopId) {
        return offerRepository.findByBarbershopId(barbershopId);
    }
}
