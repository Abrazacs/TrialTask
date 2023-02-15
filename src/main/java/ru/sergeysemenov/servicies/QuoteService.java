package ru.sergeysemenov.servicies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeysemenov.converters.PageConverter;
import ru.sergeysemenov.converters.QuoteConverter;
import ru.sergeysemenov.dtos.PageDto;
import ru.sergeysemenov.dtos.QuoteDtoExport;
import ru.sergeysemenov.dtos.QuoteDtoImport;
import ru.sergeysemenov.entities.Quote;
import ru.sergeysemenov.exceptions.NotFoundException;
import ru.sergeysemenov.repositories.QuoteRepository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteConverter quoteConverter;
    private final PageConverter pageConverter;

    public void save(QuoteDtoImport dto){
        quoteRepository.save(quoteConverter.convertToQuote(dto));
    }

    public QuoteDtoExport findById(UUID id){
        UUID trace = UUID.randomUUID();
        log.info("Request for quote by id={}, traceId={}", id, trace);
        Quote quote = quoteRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException(String.format("Quote with id=%s doesn't exist", id)));
        return quoteConverter.convertToDto(quote);
    }

    public void delete (UUID id){
        UUID trace = UUID.randomUUID();
        log.info("Request on delete of the quote with id={}, traceId={}", id, trace);
        try {
            quoteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            log.info("Quote with id={} doesn't exist", id);
        }

    }

    public List<QuoteDtoExport> findTopTenByVotes(){
        UUID trace = UUID.randomUUID();
        log.info("Top ten quotes requested, traceId={}", trace);
        return quoteRepository.findBestTenByVotes().
                stream()
                .map(quoteConverter::convertToDto)
                .toList();
    }

    public List<QuoteDtoExport> findWorstTenByVotes(){
        UUID trace = UUID.randomUUID();
        log.info("Worst ten quotes requested, traceId={}", trace);
        return quoteRepository.findWorstTenByVotes().
                stream()
                .map(quoteConverter::convertToDto)
                .toList();
    }

    public PageDto findAll(Integer pageNumber, Integer pageSize) {
        UUID trace = UUID.randomUUID();
        log.info("Request for the quotes (page number ={}, page size={}), traceId={}", pageNumber, pageSize, trace);
        if (pageNumber < 1) pageNumber = 1;
        Page<Quote> page = quoteRepository.findAll(PageRequest.of(pageNumber-1, pageSize));
        return pageConverter.convert(page);
    }

    @Transactional
    public QuoteDtoExport getRandomQuote(){
        UUID trace = UUID.randomUUID();
        log.info("Request for the random quote, traceId={}", trace);
        Quote quote = quoteRepository.getRandomQuote().orElseThrow(()->
                new NotFoundException("There are no quotes in database at the moment"));
        return quoteConverter.convertToDto(quote);
    }

    public void updateQuote(UUID id, QuoteDtoImport dto) {
        UUID trace = UUID.randomUUID();
        log.info("Request on update of the quote with id={}, traceId={}",id, trace);
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if(optionalQuote.isEmpty()){
            log.error("Error during update. Quote with id={} doesn't exist, traceId={}",id, trace);
            throw new IllegalArgumentException(String.format("Quote with id=%s doesn't exist", id));
        };
        Quote quote = optionalQuote.get();
        quote.setContent(dto.getContent());
        quote.setRank(dto.getRank());
        quote.setUpdatedAt(OffsetDateTime.now());
        quoteRepository.save(quote);
        log.info("Quote with id={} successfully updated, traceId={}",id, trace);

    }
}
