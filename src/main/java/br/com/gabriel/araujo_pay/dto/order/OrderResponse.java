package br.com.gabriel.araujo_pay.dto.order;

import br.com.gabriel.araujo_pay.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(

     Long id,
     Long customerId,
     String customerName,
     String customerEmail,
     BigDecimal total,
     OrderStatus status,
     LocalDateTime createdAt


){}
