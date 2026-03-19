package br.com.cortaai.client.model;

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
@Table(name = "SERVICE")
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BARBERSHOP", referencedColumnName = "ID", nullable = false)
    private BarbershopModel barbershop;

    @Column(name = "NM_SERVICE", length = 80, nullable = false)
    private String nmService;

    @Column(name = "DS_SERVICE", length = 255)
    private String dsService;

    @Column(name = "VL_PRICE", precision = 10, scale = 2, nullable = false)
    private BigDecimal vlPrice;

    @Column(name = "NR_DURATION_MINUTES", nullable = false)
    private Integer nrDurationMinutes;

    @Column(name = "IN_ACTIVE", nullable = false)
    private Boolean inActive;

    @Column(name = "DT_CREATED")
    private LocalDateTime dtCreated;

    @Column(name = "DT_UPDATED")
    private LocalDateTime dtUpdated;

    @Column(name = "DT_DELETED")
    private LocalDateTime dtDeleted;
}
