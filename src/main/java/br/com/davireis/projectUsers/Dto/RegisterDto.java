package br.com.davireis.projectUsers.Dto;

import br.com.davireis.projectUsers.domain.Roles;

public record RegisterDto(String login, String password, Roles role) {
}
