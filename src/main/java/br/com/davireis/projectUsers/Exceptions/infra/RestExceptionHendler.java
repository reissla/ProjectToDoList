package br.com.davireis.projectUsers.Exceptions.infra;

import br.com.davireis.projectUsers.Exceptions.userAlreadyExistsException;
import br.com.davireis.projectUsers.Exceptions.userNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHendler extends RuntimeException{

    @ExceptionHandler(userAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsExceptionHendler(userAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
