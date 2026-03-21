package br.com.cortaai.client.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DomainException extends RuntimeException {

    private final String userMessage;
    private final String debugMessage;
    private final HttpStatus httpStatus;

    public DomainException(String userMessage, String debugMessage, HttpStatus httpStatus) {
        super(debugMessage);
        this.userMessage = userMessage;
        this.debugMessage = debugMessage;
        this.httpStatus = httpStatus;
    }
}
