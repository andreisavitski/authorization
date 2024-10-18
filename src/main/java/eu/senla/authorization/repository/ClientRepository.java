package eu.senla.authorization.repository;

import eu.senla.authorization.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.login = :login")
    Optional<Client> findByLogin(@Param("login") String login);
}
