package eu.senla.authorization.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public enum ApplicationError implements AppError, Supplier<ApplicationException> {

    CLIENT_NOT_FOUND(NOT_FOUND, "Client not found"),

    ROLE_NOT_FOUND(NOT_FOUND, "Role not found"),

    THE_CLIENT_EXISTS(BAD_REQUEST, "The client is already registered");

    private final HttpStatus status;

    private final String code;

    ApplicationError(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    @Override
    public ApplicationException get() {
        return new ApplicationException(this);
    }

    @Override
    public final HttpStatus getStatus() {
        return status;
    }

    @Override
    public final String getCode() {
        return code;
    }
}
