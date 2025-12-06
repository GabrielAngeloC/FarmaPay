package br.com.gabriel.araujo_pay.dto.payment;

import br.com.gabriel.araujo_pay.domain.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequest(
    @NotNull Long orderId,
    @NotNull  @Positive BigDecimal amountPaid,
    @NotNull PaymentMethod method
)

    {}
