package ru.sergeysemenov.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuoteDtoImport {
    @Schema(description = "content of the quote", required = true, example = "bla-bla")
    private String content;
    @Schema(description = "name of the user posted this quote", required = true, example = "someUser")
    private String username;
    @Schema(description = "qty of the votes given for this quote", required = true, example = "0")
    private int rank;

}
