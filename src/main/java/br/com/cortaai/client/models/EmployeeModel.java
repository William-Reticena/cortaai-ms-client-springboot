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
@Table(name = "EMPLOYEE")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID", nullable = false)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BARBERSHOP", referencedColumnName = "ID", nullable = false)
    private BarbershopModel barbershop;

    @Column(name = "IN_AVAILABLE", nullable = false)
    private Boolean available;

    @Column(name = "DT_CREATED")
    private LocalDateTime createdAt;

    @Column(name = "DT_DELETED")
    private LocalDateTime deletedAt;
}
