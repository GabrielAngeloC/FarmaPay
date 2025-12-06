package br.com.gabriel.araujo_pay.repository;

import br.com.gabriel.araujo_pay.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {

    boolean existsByEmail(String email);
}
