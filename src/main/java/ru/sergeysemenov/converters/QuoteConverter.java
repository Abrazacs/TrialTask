package ru.sergeysemenov.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sergeysemenov.dtos.QuoteDtoExport;
import ru.sergeysemenov.dtos.QuoteDtoImport;
import ru.sergeysemenov.entities.Quote;
import ru.sergeysemenov.entities.User;
import ru.sergeysemenov.exceptions.BadRequestException;
import ru.sergeysemenov.servicies.UserService;


@Component
@RequiredArgsConstructor
public class QuoteConverter {
    private final UserService userService;

    public Quote convertToQuote(QuoteDtoImport dto){
        User user = userService.findByUsername(dto.getUsername()).orElseThrow(
                ()->new BadRequestException(String.format("User with username=%s not found", dto.getUsername())));
        return Quote.builder()
                .content(dto.getContent())
                .user(user)
                .rank(dto.getRank())
                .build();
    }

    public QuoteDtoExport convertToDto(Quote quote){
        return QuoteDtoExport.builder()
                .id(quote.getId())
                .content(quote.getContent())
                .username(quote.getUser().getUsername())
                .rank(quote.getRank())
                .build();
    }

}
