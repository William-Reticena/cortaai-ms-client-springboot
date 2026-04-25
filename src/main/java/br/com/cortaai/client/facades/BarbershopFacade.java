package br.com.cortaai.client.facades;

import br.com.cortaai.client.dtos.request.CreateBarbershopRequest;
import br.com.cortaai.client.dtos.request.UpdateBarbershopRequest;
import br.com.cortaai.client.dtos.response.CreateBarbershopResponse;
import br.com.cortaai.client.dtos.response.GetBarbershopDetailsResponse;
import br.com.cortaai.client.dtos.response.ListBarbershopResponse;
import br.com.cortaai.client.dtos.response.UpdateBarbershopResponse;
import br.com.cortaai.client.dtos.shared.EmployeeWithSpecialties;
import br.com.cortaai.client.dtos.shared.UserDto;
import br.com.cortaai.client.mappers.BarbershopMapper;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.EmployeeModel;
import br.com.cortaai.client.models.ServiceModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.services.BarbershopService;
import br.com.cortaai.client.services.EmployeeService;
import br.com.cortaai.client.services.OfferService;
import br.com.cortaai.client.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BarbershopFacade {

    private final BarbershopService barbershopService;
    private final EmployeeService employeeService;
    private final OfferService offerService;
    private final UserService userService;

    public CreateBarbershopResponse createBarbershop(UserDto userAuthenticated, CreateBarbershopRequest request) {
        UserModel user = userService.getOwner(userAuthenticated.id());

        return BarbershopMapper.toCreateBarbershopResponse(barbershopService.createBarbershop(user, request));
    }

    public UpdateBarbershopResponse updateBarbershop(UserDto userAuthenticated, UpdateBarbershopRequest request) {
        UserModel user = userService.getOwner(userAuthenticated.id());

        return BarbershopMapper.toUpdateBarbershopResponse(barbershopService.updateBarbershop(user, request));
    }

    public List<ListBarbershopResponse> listBarbershops() {
        List<BarbershopModel> barbershops = barbershopService.listBarbershops();

        return BarbershopMapper.toListBarbershopResponse(barbershops);
    }

    public GetBarbershopDetailsResponse getBarbershopDetails(Long id) {
        BarbershopModel barbershop = barbershopService.getBarbershopById(id);
        List<ServiceModel> offerServices = offerService.listOfferServicesByBarbershopId(barbershop.getId());
        List<EmployeeModel> employees = employeeService.listEmployeesByBarbershopId(barbershop.getId());

        List<EmployeeWithSpecialties> employeeSpecialties = employeeService.getEmployeesWithSpecialties(employees);

        return BarbershopMapper.toGetBarbershopDetailsResponse(barbershop, offerServices, employeeSpecialties);
    }
}
