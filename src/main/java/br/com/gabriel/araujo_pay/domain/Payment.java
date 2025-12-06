package br.com.gabriel.araujo_pay.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Table
@Entity(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    private BigDecimal amountPaid;
    @Enumerated(EnumType.STRING)

    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private LocalDateTime paidAt;
}
