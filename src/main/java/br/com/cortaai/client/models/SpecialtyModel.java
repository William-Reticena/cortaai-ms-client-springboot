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
@Table(name = "specialty")
public class SpecialtyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nm_specialty", length = 30, nullable = false)
    private String nmSpecialty;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_deleted")
    private LocalDateTime dtDeleted;
}
