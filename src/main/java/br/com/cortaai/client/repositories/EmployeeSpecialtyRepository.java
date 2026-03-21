package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.EmployeeSpecialtyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSpecialtyRepository extends JpaRepository<EmployeeSpecialtyModel, Long> {
}
