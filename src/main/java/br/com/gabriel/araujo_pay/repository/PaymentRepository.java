package br.com.gabriel.araujo_pay.repository;

import br.com.gabriel.araujo_pay.domain.Order;
import br.com.gabriel.araujo_pay.domain.Customer;
import br.com.gabriel.araujo_pay.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository {
    List<Order> findByCustomer(Customer customer);
    List<Payment> findPaymentsByOrder(Order order);

}
