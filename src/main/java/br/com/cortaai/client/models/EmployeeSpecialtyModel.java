package br.com.cortaai.client.models;

import br.com.cortaai.client.models.embed.EmployeeSpecialtyId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee_specialty")
public class EmployeeSpecialtyModel {

    @EmbeddedId
    private EmployeeSpecialtyId id;

    @MapsId("idEmployee")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", referencedColumnName = "id", nullable = false)
    private EmployeeModel employee;

    @MapsId("idSpecialty")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specialty", referencedColumnName = "id", nullable = false)
    private SpecialtyModel specialty;
}
