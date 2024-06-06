package br.com.davireis.projectUsers.Repository;

import br.com.davireis.projectUsers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.login = :login")
    Optional<User> findByEmailAndLogin(String email, String login);
}
