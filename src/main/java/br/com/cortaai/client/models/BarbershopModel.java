package br.com.cortaai.client.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_deleted")
    private LocalDateTime dtDeleted;

    @PrePersist
    public void prePersist() {
        if (dtCreated == null) dtCreated = LocalDateTime.now();
    }
}
