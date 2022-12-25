package dev.evilasio.lukedirector.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardException extends RuntimeException {

    private String name;

    private HttpStatus status;

    private Object extraInfo;

    public StandardException(String name, String message, HttpStatus status, Object extra) {
        super(message);
        this.name = name;
        this.status = status;
        this.extraInfo = extra;
    }

    public StandardException(String name, String message, HttpStatus status) {
        super(message);
        this.name = name;
        this.status = status;
    }
}
