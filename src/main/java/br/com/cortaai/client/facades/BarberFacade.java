package br.com.cortaai.client.facades;

import br.com.cortaai.client.dtos.request.CreateBarberRequest;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.EmployeeModel;
import br.com.cortaai.client.models.SpecialtyModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BarberFacade {

    private final BarbershopService barbershopService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final SpecialtyService specialtyService;

    public CreateBarberRequest createBarber(String token, CreateBarberRequest request) {
        UserModel user = userService.createUserBarber(request);
        BarbershopModel barbershop = barbershopService.getBarbershopById(Long.valueOf(token));
        EmployeeModel employee = employeeService.createEmployee(user, barbershop);
        List<SpecialtyModel> specialties = specialtyService.createOrFindSpecialties(request.specialties());
        employeeService.attachEmployeeSpecialties(employee, specialties);

        return request;
    }
}
