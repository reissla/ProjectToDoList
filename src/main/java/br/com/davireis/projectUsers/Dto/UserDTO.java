package br.com.davireis.projectUsers.Dto;

import br.com.davireis.projectUsers.domain.Task;
import br.com.davireis.projectUsers.domain.User;

import java.util.List;
import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String name;
    private String login;
    private String senha;
    private String email;
    private List<Task> tasks;

    public UserDTO() {
    }

    public UserDTO(String email, String login, String senha) {
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.senha = user.getSenha();
        this.email = user.getEmail();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
