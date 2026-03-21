package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<ServiceModel, Long> {
}
