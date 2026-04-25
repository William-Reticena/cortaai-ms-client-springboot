package br.com.cortaai.client.services;

import br.com.cortaai.client.dtos.request.CreateBarberRequest;
import br.com.cortaai.client.enums.UserRoleEnum;
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

    public UserModel createUser(UserModel user) {
        List<UserModel> conflicts = userRepository.findByDsPhoneOrDsEmail(user.getDsPhone(), user.getDsEmail());

        if (!conflicts.isEmpty()) {
            UserModel conflict = conflicts.getFirst();
            if (conflict.getDsPhone().equals(user.getDsPhone())) {
                throw new DomainException(
                        "Telefone já cadastrado",
                        "User with phone number " + user.getDsPhone() + " already exists",
                        HttpStatus.BAD_REQUEST
                );
            }

            throw new DomainException(
                    "E-mail indisponível",
                    "User with email " + user.getDsEmail() + " already exists",
                    HttpStatus.BAD_REQUEST
            );
        }

        return userRepository.save(user);
    }

    public UserModel getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DomainException(
                        "Usuário não encontrado",
                        "User not found for id: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    public UserModel getOwner(Long id) {
        UserModel user = getUserById(id);

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
        return userRepository.save(UserMapper.toUserModel(request));
    }
}
