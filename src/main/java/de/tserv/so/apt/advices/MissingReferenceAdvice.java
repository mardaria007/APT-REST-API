package de.tserv.so.apt.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import de.tserv.so.apt.exceptions.MissingReferenceException;

@RestControllerAdvice
public class MissingReferenceAdvice {
    @ExceptionHandler(MissingReferenceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String missingReferenceHandler(MissingReferenceException ex) {
        return ex.getMessage();
    }
}
