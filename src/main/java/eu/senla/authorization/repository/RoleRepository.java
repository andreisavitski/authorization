package eu.senla.authorization.repository;

import eu.senla.authorization.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.name = :name")
    Optional<Role> findByName(@Param("name") String name);
}
