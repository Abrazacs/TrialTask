package ru.sergeysemenov.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class QuoteDtoExport {
    @Schema(description = "id of the quote", required = true, example = "f20fb1a6-e4b2-48ba-840c-d083e0143fc0")
    private UUID id;
    @Schema(description = "content of the quote", required = true, example = "bla-bla")
    private String content;
    @Schema(description = "name of the user posted this quote", required = true, example = "someUser")
    private String username;
    @Schema(description = "qty of the votes given for this quote", required = true, example = "0")
    private int rank;

}
