package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {

    @Query("SELECT COUNT(a) > 0 FROM AppointmentModel a " +
            "WHERE a.employee.id = :idBarber " +
            "AND a.dtAppointment = :date " +
            "AND a.tpStatus <> 'CANCELLED' " +
            "AND (:newStart < a.hrEnd AND :newEnd > a.hrStart)")
    Boolean existsOverlappingAppointment(@Param("idBarber") Long idBarber,
                                         @Param("date") LocalDate date,
                                         @Param("newStart") LocalTime newStart,
                                         @Param("newEnd") LocalTime newEnd);
}
