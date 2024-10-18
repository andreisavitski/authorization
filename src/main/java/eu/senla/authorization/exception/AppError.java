package eu.senla.authorization.exception;

import org.springframework.http.HttpStatus;

public interface AppError {

    HttpStatus getStatus();

    String getCode();
}
