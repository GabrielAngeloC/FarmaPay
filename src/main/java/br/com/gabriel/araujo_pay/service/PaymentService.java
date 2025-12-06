package br.com.gabriel.araujo_pay.service;

import br.com.gabriel.araujo_pay.domain.Order;
import br.com.gabriel.araujo_pay.domain.OrderStatus;
import br.com.gabriel.araujo_pay.domain.Payment;
import br.com.gabriel.araujo_pay.domain.PaymentStatus;
import br.com.gabriel.araujo_pay.dto.payment.PaymentRequest;
import br.com.gabriel.araujo_pay.dto.payment.PaymentResponse;
import br.com.gabriel.araujo_pay.repository.OrderRepository;
import br.com.gabriel.araujo_pay.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public PaymentResponse create(PaymentRequest paymentRequest) {
        Order order = orderRepository.findById(paymentRequest.orderId()).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if(order.getStatus() == OrderStatus.CANCELED) {
            throw new RuntimeException("Não é possível pagar um pedido cancelado");
        }

        if(paymentRepository.existsByOrder(order)){
            throw new RuntimeException("já existe um pagamento para este pedido");
        }

        if(paymentRequest.amountPaid().compareTo(order.getTotal()) != 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Pedido não econtrado");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmountPaid(order.getTotal());
        payment.setMethod(paymentRequest.method());
        payment.setStatus(PaymentStatus.PAID);
        payment.setPaidAt(LocalDateTime.now());

        paymentRepository.save(payment);

        order.setStatus(OrderStatus.APROVED);

        orderRepository.save(order);

        return toResponse(payment);

    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PaymentResponse findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        return toResponse(payment);
    }

    @Transactional(readOnly = true)
    public PaymentResponse findByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Payment payment = paymentRepository.findByOrder(order).orElseThrow(() -> new RuntimeException("Pagamento não encontrado para esse pedido"));

        return toResponse(payment);
    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
        payment.getId(),
        payment.getOrder().getId(),
        payment.getOrder().getCustomer().getId(),
        payment.getOrder().getCustomer().getName(),
        payment.getOrder().getTotal(),
        payment.getAmountPaid(),
        payment.getMethod(),
        payment.getStatus(),
        payment.getPaidAt()
        );
    }

}
