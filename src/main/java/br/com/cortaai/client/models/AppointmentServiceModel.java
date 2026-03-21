package br.com.cortaai.client.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointment_service")
public class AppointmentServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_appointment", referencedColumnName = "id", nullable = false)
    private AppointmentModel appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", referencedColumnName = "id", nullable = false)
    private ServiceModel service;

    @Column(name = "nm_service_snapshot", length = 80)
    private String nmServiceSnapshot;

    @Column(name = "vl_price_snapshot", precision = 10, scale = 2)
    private BigDecimal vlPriceSnapshot;

    @Column(name = "nr_duration_minutes_snapshot")
    private Integer nrDurationMinutesSnapshot;
}
