package ru.tinkoff.edu.java.scrapper.controller.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.data.response.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.exception.LinkNotFoundException;

import java.util.Arrays;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                        "Некорректные параметры запроса",
                        "400",
                        ex.getClass().getSimpleName(),
                        ex.getMessage(),
                        Arrays.stream(ex.getStackTrace())
                                .map(StackTraceElement::toString)
                                .toList()
                )
        );
    }

    @ExceptionHandler(LinkNotFoundException.class)
    protected ResponseEntity<Object> handleLinkNotFoundException(
            LinkNotFoundException ex
    ) {
        return ResponseEntity.status(404).body(
                new ApiErrorResponse(
                        "Ссылка не найдена",
                        "404",
                        ex.getClass().getSimpleName(),
                        ex.getMessage(),
                        Arrays.stream(ex.getStackTrace())
                                .map(StackTraceElement::toString)
                                .toList()
                )
        );
    }

}
