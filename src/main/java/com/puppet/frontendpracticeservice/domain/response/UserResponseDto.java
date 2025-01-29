package com.puppet.frontendpracticeservice.domain.response;

import com.puppet.frontendpracticeservice.security.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Получение всех данных пользователя")
public record UserResponseDto(
        @NotBlank(message = "Имя пользователя не может быть пустым")
        String name,
        String surname,
        LocalDate birthday,
        String inn,
        String snils,
        String passport,
        String login,
        String password,
        Set<Role> roles) {
}