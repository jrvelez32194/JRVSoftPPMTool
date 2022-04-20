package jrvsoft.ppmtool.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class CustomResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleException(ProjectIdentifierException ex, WebRequest webRequest){
        ProjectIdentifierExceptionResponse projectIdentifierExceptionResponse = new ProjectIdentifierExceptionResponse(ex.getMessage());
            return  new ResponseEntity(projectIdentifierExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleExceptionSequence(ProjectSequenceException ex, WebRequest webRequest){
        ProjectSequenceExceptionResponse projectSequenceExceptionResponse = new ProjectSequenceExceptionResponse(ex.getMessage());
        return  new ResponseEntity(projectSequenceExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleExceptionPriority(PriorityException ex, WebRequest webRequest){
        PriorityExceptionResponse priorityExceptionResponse = new PriorityExceptionResponse(ex.getMessage());
        return  new ResponseEntity(priorityExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleExceptionUsernameAlreadyExist(UsernmaneAlreadyExistException ex, WebRequest webRequest){
        UsernameAlreadyExistResponse usernameAlreadyExistResponse = new UsernameAlreadyExistResponse(ex.getMessage());
        return  new ResponseEntity(usernameAlreadyExistResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleExceptionUsernameNotExist(UsernmaneNotExistException ex, WebRequest webRequest){
        UsernameNotExistResponse usernameNotExistResponse = new UsernameNotExistResponse(ex.getMessage());
        return  new ResponseEntity(usernameNotExistResponse, HttpStatus.BAD_REQUEST);
    }
}
