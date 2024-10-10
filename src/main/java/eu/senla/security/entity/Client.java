package eu.senla.security.entity;

import eu.senla.security.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static eu.senla.security.constant.AppConstants.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = CLIENT_TABLE_NAME)
public class Client {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = CLIENT_COLUMN_NAME_ID)
    private Long id;

    @Column(name = CLIENT_COLUMN_NAME_LOGIN, unique = true)
    private String login;

    @Column(name = CLIENT_COLUMN_NAME_PASSWORD)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = CLIENT_COLUMN_NAME_ROLE)
    private Role role;
}
