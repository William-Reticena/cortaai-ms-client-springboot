package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateUserRequest;
import br.com.cortaai.client.dtos.response.CreateUserResponse;
import br.com.cortaai.client.exceptions.DomainException;
import br.com.cortaai.client.mappers.UserMapper;
import br.com.cortaai.client.models.UserModel;
import br.com.cortaai.client.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
