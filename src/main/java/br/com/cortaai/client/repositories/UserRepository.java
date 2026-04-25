package br.com.cortaai.client.repositories;

import br.com.cortaai.client.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    List<UserModel> findByDsPhoneOrDsEmail(String dsPhone, String dsEmail);
}
