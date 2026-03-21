package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.SpecialtyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<SpecialtyModel, Long> {

    Optional<SpecialtyModel> findByNmSpecialtyIgnoreCase(String nmSpecialty);
}
