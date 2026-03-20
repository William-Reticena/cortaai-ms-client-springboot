package br.com.cortaai.client.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "service")
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barbershop", referencedColumnName = "id", nullable = false)
    private BarbershopModel barbershop;

    @Column(name = "nm_service", length = 80, nullable = false)
    private String nmService;

    @Column(name = "ds_service", length = 255)
    private String dsService;

    @Column(name = "vl_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal vlPrice;

    @Column(name = "nr_duration_minutes", nullable = false)
    private Integer nrDurationMinutes;

    @Column(name = "in_active", nullable = false)
    private Boolean inActive;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;

    @Column(name = "dt_deleted")
    private LocalDateTime dtDeleted;
}
