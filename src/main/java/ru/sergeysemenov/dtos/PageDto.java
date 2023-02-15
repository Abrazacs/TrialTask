package ru.sergeysemenov.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PageDto {
    @Schema(description = "current page number", required = true, example = "1")
    private int currentPage;

    @Schema(description = "total qty of the pages", required = true, example = "10")
    private int totalPages;

    @Schema(description = "list of the quotes, can be empty", required = true)
    private List<QuoteDtoExport> exportList;
}
