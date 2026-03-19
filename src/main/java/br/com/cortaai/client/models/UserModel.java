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
@Table(name = "\"USER\"")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NM_USER", nullable = false, length = 80)
    private String nmUser;

    @Column(name = "DS_PHONE", length = 20)
    private String dsPhone;

    @Column(name = "DS_PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_ROLE", nullable = false, length = 10)
    private UserRoleEnum role;

    @Column(name = "DT_CREATED")
    private LocalDateTime createdAt;

    @Column(name = "DT_DELETED")
    private LocalDateTime deletedAt;
}
