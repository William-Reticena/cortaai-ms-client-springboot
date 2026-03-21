package br.com.cortaai.client.models.embed;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeSpecialtyId implements Serializable {

    private Long idEmployee;
    private Long idSpecialty;
}
