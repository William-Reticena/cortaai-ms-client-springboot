package br.com.cortaai.client.model.embed;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeSpecialtyId {

    private Integer idEmployee;
    private Integer idSpecialty;
}
