package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.EmployeeSpecialtyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSpecialtyRepository extends JpaRepository<EmployeeSpecialtyModel, Long> {

    List<EmployeeSpecialtyModel> findAllByEmployeeIdIn(List<Long> employeeIds);
}
