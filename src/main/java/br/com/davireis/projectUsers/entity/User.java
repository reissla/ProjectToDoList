package br.com.davireis.projectUsers.entity;

import br.com.davireis.projectUsers.Dto.UserDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = true)
    private Task taskList;


    public User() {

    }

    public User(UUID id, String name, String login, String senha, String email, Task taskList) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.taskList = taskList;
    }

    public User(UserDTO userDTO) {
        id = userDTO.getId();
        name = userDTO.getName();
        login = userDTO.getLogin();
        senha = userDTO.getSenha();
        email = userDTO.getEmail();
        taskList = userDTO.getTaskList();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
