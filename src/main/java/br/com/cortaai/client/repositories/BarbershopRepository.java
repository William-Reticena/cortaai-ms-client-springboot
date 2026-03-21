package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.BarbershopModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbershopRepository extends JpaRepository<BarbershopModel, Long> {
}
