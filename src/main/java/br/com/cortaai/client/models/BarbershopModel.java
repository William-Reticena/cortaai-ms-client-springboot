package br.com.cortaai.client.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "barbershop")
public class BarbershopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner", referencedColumnName = "id", nullable = false)
    private UserModel owner;

    @Column(name = "nm_barbershop", length = 100)
    private String nmBarbershop;

    @Column(name = "ds_address", length = 255)
    private String dsAddress;

    @Column(name = "ds_phone", length = 20)
    private String dsPhone;

    @Column(name = "hr_closes")
    private LocalTime hrCloses;

    @Column(name = "hr_opens")
    private LocalTime hrOpens;

    @Column(name = "in_open")
    private Boolean inOpen;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_deleted")
    private LocalDateTime dtDeleted;

    @PrePersist
    public void prePersist() {
        if (dtCreated == null) dtCreated = LocalDateTime.now();
    }
}
