package br.com.gabriel.araujo_pay.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
public record OrderRequest(
        Long customerId,
        @NotNull @Positive BigDecimal total
) {
}
