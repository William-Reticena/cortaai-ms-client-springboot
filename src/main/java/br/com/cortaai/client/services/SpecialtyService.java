package br.com.cortaai.client.services;

import br.com.cortaai.client.models.SpecialtyModel;
import br.com.cortaai.client.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public List<SpecialtyModel> createOrFindSpecialties(List<String> specialties) {
        List<SpecialtyModel> result = new ArrayList<>();

        for (String specialtyName : specialties) {
            SpecialtyModel specialty = specialtyRepository
                    .findByNmSpecialtyIgnoreCase(specialtyName)
                    .orElseGet(() -> specialtyRepository
                            .save(SpecialtyModel.builder()
                                    .nmSpecialty(specialtyName)
                                    .build()
                            )
                    );

            result.add(specialty);
        }

        return result;
    }
}
