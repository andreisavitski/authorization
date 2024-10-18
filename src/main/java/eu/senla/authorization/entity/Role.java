package eu.senla.authorization.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "role", cascade = REMOVE)
    private List<Client> clients;
}
