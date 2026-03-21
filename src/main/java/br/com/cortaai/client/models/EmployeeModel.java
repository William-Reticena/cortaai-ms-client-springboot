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
@Table(name = "employee")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barbershop", referencedColumnName = "id", nullable = false)
    private BarbershopModel barbershop;

    @Column(name = "in_available", nullable = false)
    private Boolean inAvailable;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_deleted")
    private LocalDateTime dtDeleted;

    @PrePersist
    public void prePersist() {
        if (dtCreated == null) dtCreated = LocalDateTime.now();
    }
}
