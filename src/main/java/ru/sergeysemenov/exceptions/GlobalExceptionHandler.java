package ru.sergeysemenov.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> handleBadRequestException(BadRequestException e){
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.name(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError>handleIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.name(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}