package br.com.davireis.projectUsers.Repository;

import br.com.davireis.projectUsers.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTittle(String tittle);
}
