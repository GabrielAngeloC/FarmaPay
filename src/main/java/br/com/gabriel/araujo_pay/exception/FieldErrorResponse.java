package br.com.gabriel.araujo_pay.exception;

public record FieldErrorResponse(
        String field,
        String message) {
}
