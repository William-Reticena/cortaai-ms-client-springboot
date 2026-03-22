package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.AppointmentServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentServiceRepository extends JpaRepository<AppointmentServiceModel, Long> {
}
