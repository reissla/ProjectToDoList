package br.com.davireis.projectUsers.Repository;

import br.com.davireis.projectUsers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.login = :login")
    Optional<User> findByEmailAndLogin(String email, String login);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findById(@Param("id") UUID id);

}
