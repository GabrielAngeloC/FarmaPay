package br.com.gabriel.araujo_pay.controller;

import br.com.gabriel.araujo_pay.dto.order.*;
import br.com.gabriel.araujo_pay.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse create(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.create(orderRequest);
    }

    @GetMapping
    public List<OrderResponse> listAll(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id){
        return orderService.findById(id);
    }
    @GetMapping("/by-customer/{customerId}")
    public List<OrderResponse> getByCustomer(@PathVariable Long customerId) {
        return orderService.findByCustomerId(customerId);
    }

    @PutMapping("/{id}/status")
    public OrderResponse updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderStatusUpdateRequest request
    ) {
        return orderService.updateStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
