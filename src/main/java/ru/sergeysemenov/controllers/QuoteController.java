package ru.sergeysemenov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sergeysemenov.dtos.PageDto;
import ru.sergeysemenov.dtos.QuoteDtoExport;
import ru.sergeysemenov.dtos.QuoteDtoImport;
import ru.sergeysemenov.exceptions.AppError;
import ru.sergeysemenov.servicies.QuoteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/quotes")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @Operation(
            summary = "Request for the list of declarations",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PageDto.class))
                    )
            }
    )
    @GetMapping()
    public PageDto receiveAllQuotes(
            @RequestParam(name = "p", defaultValue = "1")
            @Parameter(description = "Number of the page") Integer page,
            @RequestParam(name = "page_size", defaultValue = "10")
            @Parameter(description = "Qty of quotes per page") Integer pageSize){
        return quoteService.findAll(page, pageSize);
    }

    @Operation(
            summary = "Request for the quote by id",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = QuoteDtoExport.class))
                    ),
                    @ApiResponse(
                            description = "Quote with requested id doesn't exist", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public QuoteDtoExport findById(@PathVariable @Parameter(description = "Quote id", required = true) UUID id){
        return quoteService.findById(id);
    }

    @Operation(
            summary = "Request for the top ten quotes ranked by votes",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = QuoteDtoExport.class))
                    )
            }
    )
    @GetMapping("/top-ten")
    public List<QuoteDtoExport> findTopTen(){
        return quoteService.findTopTenByVotes();
    }

    @Operation(
            summary = "Request for the worst ten quotes ranked by votes",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = QuoteDtoExport.class))
                    )
            }
    )
    @GetMapping("/worst-ten")
    public List<QuoteDtoExport> findWorstTen(){
        return quoteService.findWorstTenByVotes();
    }

    @Operation(
            summary = "Request for the random quote",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = QuoteDtoExport.class))
                    ),
                    @ApiResponse(
                            description = "There are no quotes in db", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/random")
    public QuoteDtoExport findRandomQuote(){
        return quoteService.getRandomQuote();
    }

    @Operation(
            summary = "Request for adding a new quote",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<HttpStatus> saveNewQuote(
           @RequestBody @Parameter(description = "Data about new quote", required = true) QuoteDtoImport dto){
        quoteService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Request to delete the quote by id",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuote(
            @PathVariable @Parameter(description = "Quote id", required = true) UUID id){
        quoteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Request to update the quote by id",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Quote with requested id doesn't exist", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateQuote(
            @PathVariable @Parameter(description = "Quote id", required = true) UUID id,
            @RequestBody @Parameter(description = "Data about quote", required = true) QuoteDtoImport dto
    ){
        quoteService.updateQuote(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
