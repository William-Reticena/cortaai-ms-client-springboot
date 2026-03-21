package br.com.cortaai.client.services;

import br.com.cortaai.client.mappers.EmployeeMapper;
import br.com.cortaai.client.models.*;
import br.com.cortaai.client.models.embed.EmployeeSpecialtyId;
import br.com.cortaai.client.repositories.EmployeeRepository;
import br.com.cortaai.client.repositories.EmployeeSpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeSpecialtyRepository employeeSpecialtyRepository;

    public EmployeeModel createEmployee(UserModel user, BarbershopModel barbershop) {
        return employeeRepository.save(EmployeeMapper.toModel(user, barbershop));
    }

    public List<EmployeeSpecialtyModel> attachEmployeeSpecialties(EmployeeModel employee, List<SpecialtyModel> specialties) {
        List<EmployeeSpecialtyModel> result = new ArrayList<>();

        for (SpecialtyModel specialty : specialties) {
            EmployeeSpecialtyModel model = employeeSpecialtyRepository.save(EmployeeSpecialtyModel.builder()
                    .id(new EmployeeSpecialtyId(employee.getId(), specialty.getId()))
                    .employee(employee)
                    .specialty(specialty)
                    .build());

            result.add(model);
        }

        return result;
    }
}
