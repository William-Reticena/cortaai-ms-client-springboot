package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateBarberRequest;
import br.com.cortaai.client.dtos.request.CreateUserRequest;
import br.com.cortaai.client.dtos.response.CreateUserResponse;
import br.com.cortaai.client.enums.UserRoleEnum;
import br.com.cortaai.client.exceptions.DomainException;
import br.com.cortaai.client.mappers.UserMapper;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByDsPhone(request.dsPhone())) {
            throw new DomainException(
                    "Telefone já cadastrado",
                    "User with phone number " + request.dsPhone() + " already exists",
                    HttpStatus.BAD_REQUEST
            );
        }

        UserModel model = userRepository.save(UserMapper.toModel(request));

        return UserMapper.toResponse(model);
    }

    public UserModel getOwner(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new DomainException(
                        "Usuário não encontrado",
                        "User not found for id: " + id,
                        HttpStatus.NOT_FOUND
                ));

        if (user.getTpRole() != UserRoleEnum.OWNER) {
            throw new DomainException(
                    "Acesso negado",
                    "User with id " + id + " does not have OWNER role",
                    HttpStatus.FORBIDDEN
            );
        }

        return user;
    }

    public UserModel createUserBarber(CreateBarberRequest request) {
        return userRepository.save(UserMapper.toModel(request));
    }
}
