package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<ServiceModel, Long> {

    List<ServiceModel> findByBarbershopId(Long barbershopId);
}
