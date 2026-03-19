package br.com.cortaai.client.model;

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
@Table(name = "APPOINTMENT_SERVICE")
public class AppointmentServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_APPOINTMENT", referencedColumnName = "ID", nullable = false)
    private AppointmentModel appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SERVICE", referencedColumnName = "ID", nullable = false)
    private ServiceModel service;

    @Column(name = "NM_SERVICE_SNAPSHOT", length = 80)
    private String nmServiceSnapshot;

    @Column(name = "VL_PRICE_SNAPSHOT", precision = 10, scale = 2)
    private BigDecimal vlPriceSnapshot;

    @Column(name = "NR_DURATION_MINUTES_SNAPSHOT")
    private Integer nrDurationMinutesSnapshot;
}
