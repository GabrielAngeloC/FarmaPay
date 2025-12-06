package br.com.gabriel.araujo_pay.repository;

import br.com.gabriel.araujo_pay.domain.Order;
import br.com.gabriel.araujo_pay.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    boolean existsByOrder(Order order);
    Optional<Payment> findByOrder(Order order);

}
