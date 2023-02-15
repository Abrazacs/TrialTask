package ru.sergeysemenov.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.sergeysemenov.dtos.PageDto;
import ru.sergeysemenov.dtos.QuoteDtoExport;
import ru.sergeysemenov.entities.Quote;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PageConverter {
    private final QuoteConverter quoteConverter;

    public PageDto convert(Page<Quote> page){
        List<QuoteDtoExport> quoteDtoExports = page.get()
                .map(quoteConverter::convertToDto)
                .toList();

        return PageDto.builder()
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .exportList(quoteDtoExports)
                .build();
    }
}
