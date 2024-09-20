package br.com.davireis.projectUsers.Repository;

import br.com.davireis.projectUsers.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    @Query("SELECT t FROM Task t WHERE t.userId = :userId")
    List<Task> findAllTasksByUserId(@Param("userId") UUID userId);
}
