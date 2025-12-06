package br.com.gabriel.araujo_pay.service;

import br.com.gabriel.araujo_pay.domain.Customer;
import br.com.gabriel.araujo_pay.dto.customer.CustomerRequest;
import br.com.gabriel.araujo_pay.dto.customer.CustomerResponse;
import br.com.gabriel.araujo_pay.exception.BusinessException;
import br.com.gabriel.araujo_pay.exception.NotFoundException;
import br.com.gabriel.araujo_pay.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerResponse create(CustomerRequest request) {

        if (repository.existsByEmail(request.getEmail())){
            throw new BusinessException("email já cadastrado");
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setDocument(request.getDocument());

        repository.save(customer);

        return toResponse(customer);
    }

@Transactional
    public CustomerResponse update(Long Id, CustomerRequest request) {
        Customer customer = repository.findById(Id).orElseThrow( () -> new BusinessException("Id inválido"));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setDocument(request.getDocument());

        repository.save(customer);

        return toResponse(customer);
    }

    @Transactional
    public List<CustomerResponse> findAll() {
        List<Customer> customers = repository.findAll();
        if (customers.isEmpty()) {
            throw new NotFoundException("Não há Clientes");
        }
        return customers.stream().map(this::toResponse).toList();
    }

    @Transactional
    public CustomerResponse findById(Long Id) {
        Customer customer = repository.findById(Id).orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        return toResponse(customer);
    }

    @Transactional
    public void delete(Long Id) {
        if (!repository.existsById(Id)) {
            throw new NotFoundException("Cliente não encontrado");
        }
        repository.deleteById(Id);
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
        customer.getId(),
        customer.getName(),
        customer.getEmail(),
        customer.getDocument());
    }

}
