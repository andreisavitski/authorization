package eu.senla.security.exception;

import org.springframework.http.HttpStatus;

public interface AppError {

    HttpStatus getStatus();

    String getCode();
}
