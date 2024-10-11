package eu.senla.security.controller.advice;

import eu.senla.security.dto.exception.ErrorResponseDto;
import eu.senla.security.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    private ResponseEntity<ErrorResponseDto> handleApplicationException(ApplicationException applicationException) {
        return ResponseEntity.status(
                applicationException.getStatus()).body(
                new ErrorResponseDto(applicationException.getStatus(), applicationException.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponseDto> handleBaseException(Exception exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDto(INTERNAL_SERVER_ERROR, exception.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException validException) {
        return ResponseEntity.status(validException.getStatusCode()).body(
                new ErrorResponseDto(validException.getStatusCode(), validException.getMessage())
        );
    }
}
