package br.com.cortaai.client.mappers;

import br.com.cortaai.client.dtos.request.CreateAppointmentRequest;
import br.com.cortaai.client.dtos.response.CreateAppointmentResponse;
import br.com.cortaai.client.enums.AppointmentStatusEnum;
import br.com.cortaai.client.models.*;

import java.time.LocalTime;

public class AppointmentMapper {

    public static AppointmentModel toAppointmentModel(UserModel user, EmployeeModel employee, ServiceModel service, CreateAppointmentRequest request) {
        LocalTime hrStart = request.dtAppointment().toLocalTime();
        LocalTime hrEnd = hrStart.plusMinutes(service.getNrDurationMinutes());

        return AppointmentModel.builder()
                .client(user)
                .barbershop(employee.getBarbershop())
                .employee(employee)
                .dtAppointment(request.dtAppointment().toLocalDate())
                .hrStart(hrStart)
                .hrEnd(hrEnd)
                .tpStatus(AppointmentStatusEnum.PENDING)
                .dsNotes(request.dsNotes())
                .build();
    }

    public static CreateAppointmentResponse toCreateAppointmentResponse(AppointmentModel appointment) {
        return CreateAppointmentResponse.builder()
                .nmBarbershop(appointment.getBarbershop().getNmBarbershop())
                .nmBarber(appointment.getEmployee().getUser().getNmUser())
                .dtAppointment(appointment.getDtAppointment())
                .hrStart(appointment.getHrStart())
                .hrEnd(appointment.getHrEnd())
                .dsNotes(appointment.getDsNotes())
                .tpStatus(appointment.getTpStatus())
                .build();
    }

    public static AppointmentServiceModel toAppointmentServiceModel(AppointmentModel appointment, ServiceModel service) {
        return AppointmentServiceModel.builder()
                .appointment(appointment)
                .service(service)
                .nmServiceSnapshot(service.getNmService())
                .vlPriceSnapshot(service.getVlPrice())
                .nrDurationMinutesSnapshot(service.getNrDurationMinutes())
                .build();
    }
}
