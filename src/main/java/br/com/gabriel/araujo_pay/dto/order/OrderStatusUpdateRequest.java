package br.com.gabriel.araujo_pay.dto.order;

import br.com.gabriel.araujo_pay.domain.OrderStatus;

public record OrderStatusUpdateRequest
        (OrderStatus status) {
}
