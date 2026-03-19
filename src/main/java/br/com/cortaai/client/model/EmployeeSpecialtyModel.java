package br.com.cortaai.client.model;

import br.com.cortaai.client.model.embed.EmployeeSpecialtyId;
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
@Table(name = "EMPLOYEE_SPECIALTY")
public class EmployeeSpecialtyModel {

    @EmbeddedId
    private EmployeeSpecialtyId id;

    @MapsId("idEmployee")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLOYEE", referencedColumnName = "ID", nullable = false)
    private EmployeeModel employee;

    @MapsId("idSpecialty")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SPECIALTY", referencedColumnName = "ID", nullable = false)
    private SpecialtyModel specialty;
}
