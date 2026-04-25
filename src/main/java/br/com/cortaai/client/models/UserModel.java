package br.com.cortaai.client.models;

import br.com.cortaai.client.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class UserModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nm_user", nullable = false, length = 80)
    private String nmUser;

    @Column(name = "ds_phone", length = 20)
    private String dsPhone;

    @Column(name = "ds_email", length = 100)
    private String dsEmail;

    @Column(name = "ds_password", nullable = false)
    private String dsPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_role", nullable = false, length = 10)
    private UserRoleEnum tpRole;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_deleted")
    private LocalDateTime dtDeleted;

    @PrePersist
    public void prePersist() {
        if (dtCreated == null) dtCreated = LocalDateTime.now();
    }
}
