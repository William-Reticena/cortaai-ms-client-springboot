package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateOfferRequest;
import br.com.cortaai.client.mappers.OfferMapper;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.ServiceModel;
import br.com.cortaai.client.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public ServiceModel createOffer(BarbershopModel barbershop, CreateOfferRequest request) {
        return offerRepository.save(OfferMapper.toModel(barbershop, request));
    }
}
