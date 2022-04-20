package jrvsoft.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectSequenceException extends  RuntimeException {
    public ProjectSequenceException(String message) {
        super(message);
    }
}
