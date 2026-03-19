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
@Table(name = "APPOINTMENT")
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID", nullable = false)
    private UserModel client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BARBERSHOP", referencedColumnName = "ID", nullable = false)
    private BarbershopModel barbershop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLOYEE", referencedColumnName = "ID", nullable = false)
    private EmployeeModel idEmployee;

    @Column(name = "DT_APPOINTMENT", nullable = false)
    private LocalDate dtAppointment;

    @Column(name = "HR_START", nullable = false)
    private LocalTime hrStart;

    @Column(name = "HR_END", nullable = false)
    private LocalTime hrEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_STATUS", nullable = false)
    private AppointmentStatusEnum tpStatus;

    @Column(name = "DS_NOTES", length = 255)
    private String dsNotes;

    @Column(name = "DT_CREATED")
    private LocalDateTime dtCreated;

    @Column(name = "DT_UPDATED")
    private LocalDateTime dtUpdated;

    @Column(name = "DT_DELETED")
    private LocalDateTime dtDeleted;

}
