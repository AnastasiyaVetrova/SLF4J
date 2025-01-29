package com.puppet.frontendpracticeservice.handler;

import com.puppet.frontendpracticeservice.domain.response.SimpleMessage;
import com.puppet.frontendpracticeservice.exception.AuthException;
import com.puppet.frontendpracticeservice.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new UserNotFoundException("Пользователь не найден");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected SimpleMessage handleMethodArgumentNotValid(IllegalArgumentException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(InvalidMediaTypeException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    protected SimpleMessage handleMethodArgumentNotValid(InvalidMediaTypeException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected SimpleMessage handleUserNotFoundException(UserNotFoundException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected SimpleMessage handleAuthException(AuthException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected SimpleMessage handleJwtException(JwtException exception) {
        return new SimpleMessage(exception.getMessage());
    }

}
