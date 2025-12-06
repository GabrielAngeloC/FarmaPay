package br.com.gabriel.araujo_pay.controller;

import br.com.gabriel.araujo_pay.domain.Payment;
import br.com.gabriel.araujo_pay.dto.payment.*;
import br.com.gabriel.araujo_pay.service.PaymentService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse create(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.create(paymentRequest);
    }

    @GetMapping
    public List<PaymentResponse> listAll() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @GetMapping("/by-order/{orderId}")
    public PaymentResponse getByOrder(@PathVariable Long orderId) {
        return paymentService.findByOrderId(orderId);
    }

}
