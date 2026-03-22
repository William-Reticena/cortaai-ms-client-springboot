package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.shared.EmployeeWithSpecialties;
import br.com.cortaai.client.exceptions.DomainException;
import br.com.cortaai.client.mappers.EmployeeMapper;
import br.com.cortaai.client.models.*;
import br.com.cortaai.client.models.embed.EmployeeSpecialtyId;
import br.com.cortaai.client.repositories.EmployeeRepository;
import br.com.cortaai.client.repositories.EmployeeSpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeSpecialtyRepository employeeSpecialtyRepository;

    public EmployeeModel createEmployee(UserModel user, BarbershopModel barbershop) {
        return employeeRepository.save(EmployeeMapper.toModel(user, barbershop));
    }

    public EmployeeModel getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new DomainException(
                        "Funcionário não encontrado",
                        "Employee not found for id: " + id,
                        HttpStatus.NOT_FOUND
                ));
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

    public List<EmployeeModel> listEmployeesByBarbershopId(Long barbershopId) {
        return employeeRepository.findByBarbershopId(barbershopId);
    }

    public List<EmployeeWithSpecialties> getEmployeesWithSpecialties(List<EmployeeModel> employees) {
        List<Long> employeeIds = employees.stream().map(EmployeeModel::getId).toList();
        List<EmployeeSpecialtyModel> allRelations = employeeSpecialtyRepository.findAllByEmployeeIdIn(employeeIds);

        Map<Long, List<String>> specialtiesMap = allRelations.stream()
                .collect(Collectors.groupingBy(
                        rel -> rel.getEmployee().getId(),
                        Collectors.mapping(rel -> rel.getSpecialty().getNmSpecialty(), Collectors.toList())
                ));

        return employees.stream()
                .map(emp -> EmployeeWithSpecialties.builder()
                        .id(emp.getId())
                        .nmBarber(emp.getUser().getNmUser())
                        .specialties(specialtiesMap.getOrDefault(emp.getId(), Collections.emptyList()))
                        .build())
                .toList();
    }
}
