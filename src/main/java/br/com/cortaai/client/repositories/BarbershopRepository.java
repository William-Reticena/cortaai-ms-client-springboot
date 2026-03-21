package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.BarbershopModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarbershopRepository extends JpaRepository<BarbershopModel, Long> {

    Optional<BarbershopModel> findByOwnerId(Long id);
}
