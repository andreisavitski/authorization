package eu.senla.authorization.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "permission_client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;
}
