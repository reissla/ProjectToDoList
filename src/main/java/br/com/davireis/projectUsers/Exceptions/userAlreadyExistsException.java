package br.com.davireis.projectUsers.Exceptions;

public class userAlreadyExistsException extends RuntimeException{

    public userAlreadyExistsException() {
        super("User Already Exists in data base!");
    }

    public userAlreadyExistsException(String message) {
        super(message);
    }
}
