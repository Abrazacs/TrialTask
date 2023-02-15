package ru.sergeysemenov.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @Schema(description = "name of the user", example = "admin")
    private String username;
    @Schema(description = "password", example = "some bad password")
    private String password;
    @Schema(description = "e-mail", example = "mail@mail.com")
    private String email;
}
