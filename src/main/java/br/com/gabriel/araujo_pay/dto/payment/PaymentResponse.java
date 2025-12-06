package br.com.gabriel.araujo_pay.dto.payment;

import br.com.gabriel.araujo_pay.domain.PaymentMethod;
import br.com.gabriel.araujo_pay.domain.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long orderId,
        Long customerId,
        String customerName,
        BigDecimal orderAmount,
        BigDecimal totalPaid,
        PaymentMethod method,
        PaymentStatus status,
        LocalDateTime paidAt

){}
