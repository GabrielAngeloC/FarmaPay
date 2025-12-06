package br.com.gabriel.araujo_pay.service;

import br.com.gabriel.araujo_pay.domain.Order;
import br.com.gabriel.araujo_pay.domain.OrderStatus;
import br.com.gabriel.araujo_pay.domain.Customer;
import br.com.gabriel.araujo_pay.dto.order.OrderRequest;
import br.com.gabriel.araujo_pay.dto.order.OrderResponse;
import br.com.gabriel.araujo_pay.dto.order.OrderStatusUpdateRequest;
import br.com.gabriel.araujo_pay.repository.OrderRepository;
import br.com.gabriel.araujo_pay.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public OrderResponse create(OrderRequest request){
        Customer customer = customerRepository.findById(request.customerId()).orElseThrow(() -> new RuntimeException("Clinete não encotrado"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setTotal(request.total());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        orderRepository.save(order);

        return toResponse(order);
    }



    @Transactional
    public OrderResponse updateStatus(Long Id, OrderStatusUpdateRequest  request) {
        Order order = orderRepository.findById(Id).orElseThrow( () -> new RuntimeException("Pedido não encontrado"));

        order.setStatus(request.status());

        orderRepository.save(order);

        return toResponse(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll(){
        return orderRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(Long Id){
        Order order = orderRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não econtrado"));

        return toResponse(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findByCustomerId(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Cliente não econtrado"));

        return orderRepository.findByCustomer(customer).stream().map(this::toResponse).toList();

    }

    @Transactional
    public void delete(Long Id){
        if (!orderRepository.existsById(Id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        orderRepository.deleteById(Id);
    }


    private OrderResponse toResponse(Order order){

        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getCustomer().getName(),
                order.getCustomer().getEmail(),
                order.getTotal(),
                order.getStatus()
        );
    }
}
