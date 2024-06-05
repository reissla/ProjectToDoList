package br.com.davireis.projectUsers.Dto;

import br.com.davireis.projectUsers.entity.Task;

import java.time.LocalDateTime;

public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public TaskDTO(Task task){
        id = task.getId();
        title = task.getTitle();
        description = task.getDescription();
        completed = task.isCompleted();
        dueDate = task.getDueDate();
        createdDate = task.getCreatedDate();
        updatedDate = task.getUpdatedDate();
    }

    public TaskDTO(){

    }

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
}
