package br.com.gabriel.araujo_pay.dto.payment;

import br.com.gabriel.araujo_pay.domain.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record PaymentStatusUpdateRequest(
        @NotNull PaymentStatus status
) {
}
