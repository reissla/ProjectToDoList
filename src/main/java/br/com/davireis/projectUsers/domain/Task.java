package br.com.davireis.projectUsers.domain;

import br.com.davireis.projectUsers.Dto.TaskDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @Column(name = "dueDate", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "createdDate", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedDate", nullable = false)
    private LocalDateTime updatedDate;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    //Constructors:
    public Task(TaskDTO taskDTO) {
        this.id = taskDTO.getId();
        this.title = taskDTO.getTitle();
        this.description = taskDTO.getDescription();
        this.completed = taskDTO.isCompleted();
        this.dueDate = taskDTO.getDueDate();
        this.createdDate = taskDTO.getCreatedDate();
        this.updatedDate = taskDTO.getUpdatedDate();
        this.userId = taskDTO.getUserId();
    }

    public Task(Long id, String title, String description, boolean completed, LocalDateTime dueDate, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Task(Long id, String title, String description, boolean completed, LocalDateTime dueDate, LocalDateTime createdDate, LocalDateTime updatedDate, UUID userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.userId= userId;
    }

    public Task() {
    }

    //Getters and Setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
