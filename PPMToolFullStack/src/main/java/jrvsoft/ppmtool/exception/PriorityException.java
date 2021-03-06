package jrvsoft.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PriorityException extends  RuntimeException {
    public PriorityException(String message) {
        super(message);
    }
}
