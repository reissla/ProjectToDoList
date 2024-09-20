package br.com.davireis.projectUsers.Dto;

import java.util.UUID;

public record LoginResponse(String token, UUID userId) {
}
