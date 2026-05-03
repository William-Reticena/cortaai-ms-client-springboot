package br.com.cortaai.client.facades;

import br.com.cortaai.client.dtos.request.CreateAppointmentRequest;
import br.com.cortaai.client.dtos.response.CreateAppointmentResponse;
import br.com.cortaai.client.dtos.shared.UserDto;
import br.com.cortaai.client.mappers.AppointmentMapper;
import br.com.cortaai.client.models.AppointmentModel;
import br.com.cortaai.client.models.EmployeeModel;
import br.com.cortaai.client.models.ServiceModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentFacade {

    private final AppointmentService appointmentService;
    private final EmployeeService employeeService;
    private final OfferService offerService;
    private final UserService userService;

    public CreateAppointmentResponse createAppointment(UserDto userAuthenticated, CreateAppointmentRequest request) {
        ServiceModel service = offerService.getOfferServiceById(request.idService());
        appointmentService.verifyIfScheduleAvailable(request.idBarber(), request.dtAppointment(), service.getNrDurationMinutes());

        UserModel client = userService.getUserById(userAuthenticated.id());
        EmployeeModel employee = employeeService.getEmployeeById(request.idBarber());
        AppointmentModel appointment = appointmentService.createAppointment(AppointmentMapper.toAppointmentModel(client, employee, service, request));
        appointmentService.attachToService(AppointmentMapper.toAppointmentServiceModel(appointment, service));

        return AppointmentMapper.toCreateAppointmentResponse(appointment);
    }
}
