package br.com.cortaai.client.models;

import br.com.cortaai.client.enums.AppointmentStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointment")
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private UserModel client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barbershop", referencedColumnName = "id", nullable = false)
    private BarbershopModel barbershop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", referencedColumnName = "id", nullable = false)
    private EmployeeModel employee;

    @Column(name = "dt_appointment", nullable = false)
    private LocalDate dtAppointment;

    @Column(name = "hr_start", nullable = false)
    private LocalTime hrStart;

    @Column(name = "hr_end", nullable = false)
    private LocalTime hrEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_status", nullable = false)
    private AppointmentStatusEnum tpStatus;

    @Column(name = "ds_notes", length = 255)
    private String dsNotes;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;

    @Column(name = "dt_deleted")
    private LocalDateTime dtDeleted;

    @PrePersist
    public void prePersist() {
        if (dtCreated == null) dtCreated = LocalDateTime.now();
    }
}
