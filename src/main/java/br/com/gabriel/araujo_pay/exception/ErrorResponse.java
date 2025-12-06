package br.com.gabriel.araujo_pay.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<FieldErrorResponse> errors;

    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = null;
    }

    public ErrorResponse(int status, List<FieldErrorResponse> errors, String path) {
        this.status = status;
        this.errors = errors;
        this.path = path;
        this.message = "Validation error";
    }
}
