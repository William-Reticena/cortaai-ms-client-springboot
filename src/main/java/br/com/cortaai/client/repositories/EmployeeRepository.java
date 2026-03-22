package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

    List<EmployeeModel> findByBarbershopId(Long barbershopId);
}
