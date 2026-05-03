package br.com.cortaai.client.facades;

import br.com.cortaai.client.dtos.feign.response.RegisterFeignResponse;
import br.com.cortaai.client.dtos.request.CreateUserRequest;
import br.com.cortaai.client.dtos.response.CreateUserResponse;
import br.com.cortaai.client.dtos.response.MeResponse;
import br.com.cortaai.client.dtos.shared.UserDto;
import br.com.cortaai.client.mappers.AuthFeignMapper;
import br.com.cortaai.client.mappers.UserMapper;
import br.com.cortaai.client.models.BarbershopModel;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.services.AuthService;
import br.com.cortaai.client.services.BarbershopService;
import br.com.cortaai.client.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final AuthService authService;
    private final BarbershopService barbershopService;
    private final UserService userService;

    public CreateUserResponse createUser(CreateUserRequest request) {
        RegisterFeignResponse feignResponse = authService.register(AuthFeignMapper.toRegisterRequest(request));

        return UserMapper.toCreateUserResponse(userService.createUser(UserMapper.toUserModel(request, feignResponse)));
    }

    public MeResponse getCurrentUser(UserDto userAuthenticated) {
        UserModel userModel = userService.getUserById(userAuthenticated.id());
        BarbershopModel barbershopModel = barbershopService.getBarbershopByOwnerIdOrNull(userModel.getId());

        return UserMapper.toMeResponse(userModel, barbershopModel);
    }
}
