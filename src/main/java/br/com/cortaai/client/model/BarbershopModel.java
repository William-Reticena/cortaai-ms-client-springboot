package br.com.cortaai.client.model;

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
@Table(name = "BARBERSHOP")
public class BarbershopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OWNER", nullable = false)
    private UserModel owner;

    @Column(name = "NM_BARBERSHOP", length = 100)
    private String nmBarbershop;

    @Column(name = "DT_CREATED")
    private LocalDateTime dtCreated;

    @Column(name = "DT_DELETED")
    private LocalDateTime dtDeleted;
}
