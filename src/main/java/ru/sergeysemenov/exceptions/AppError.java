package ru.sergeysemenov.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AppError {
    @Schema(description = "Error code", required = true, example = "RESOURCE_NOT_FOUND")
    private final String code;

    @Schema(description = "Error description", required = true,
            example = "This login is occupied. Try to use another one")
    private final String message;

}
