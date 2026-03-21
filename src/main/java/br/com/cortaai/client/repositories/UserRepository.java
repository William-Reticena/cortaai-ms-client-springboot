package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Boolean existsByDsPhone(String dsPhone);
}
