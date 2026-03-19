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
@Table(name = "SPECIALTY")
public class SpecialtyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NM_SPECIALTY", length = 30, nullable = false)
    private String nmSpecialty;

    @Column(name = "DT_CREATED")
    private LocalDateTime dtCreated;

    @Column(name = "DT_DELETED")
    private LocalDateTime dtDeleted;
}
