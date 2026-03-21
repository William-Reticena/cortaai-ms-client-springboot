package br.com.cortaai.client.mappers;

import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.EmployeeModel;
import br.com.cortaai.client.models.UserModel;

public class EmployeeMapper {

    public static EmployeeModel toModel(UserModel user, BarbershopModel barbershop) {
        return EmployeeModel.builder()
                .user(user)
                .barbershop(barbershop)
                .inAvailable(Boolean.FALSE)
                .build();
    }
}
