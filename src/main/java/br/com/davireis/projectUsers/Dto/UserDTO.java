package br.com.davireis.projectUsers.Dto;

import br.com.davireis.projectUsers.entity.Task;
import br.com.davireis.projectUsers.entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String name;
    private String login;
    private String senha;
    private String email;
    private Task taskList;

    public UserDTO (){

    }

    public UserDTO(String email, String login, String senha) {
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public UserDTO (User user){
        id = user.getId();
        name = user.getName();
        login = user.getLogin();
        senha = user.getSenha();
        email = user.getEmail();
        taskList = user.getTaskList();
    }

    public Task getTaskList() {
        return taskList;
    }

    public void setTaskList(Task taskList) {
        this.taskList = taskList;
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
}
