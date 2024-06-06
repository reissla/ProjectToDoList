package br.com.davireis.projectUsers.Dto;

import br.com.davireis.projectUsers.entity.User;

public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private String senha;
    private String email;

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
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
