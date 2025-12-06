package br.com.gabriel.araujo_pay.repository;

import br.com.gabriel.araujo_pay.domain.Order;
import br.com.gabriel.araujo_pay.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
}
