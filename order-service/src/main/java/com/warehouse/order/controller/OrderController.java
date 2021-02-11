package com.warehouse.order.controller;

import com.warehouse.order.common.ResponseTemplate;
import com.warehouse.order.entity.Order;
import com.warehouse.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/order/")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order, BindingResult errors){
        if(errors.hasErrors())
            orderService.createErrorsMessage(errors);
        Order savedOrder = orderService.saveOrder(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getOrderId())
                .toUri();
        return ResponseEntity.created(location).body(savedOrder);
    }

    @GetMapping("{id}")
    public ResponseTemplate getOrderWithDetailsById(@PathVariable Long id){
        return orderService.getOrderWithDetailsById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id,@Valid @RequestBody Order order, BindingResult errors){
        if(errors.hasErrors())
            orderService.createErrorsMessage(errors);
        Order updatedOrder = orderService.updateOrder(order, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build().toUri();
        return ResponseEntity.created(location).body(updatedOrder);
    }

    @PatchMapping("{id}")
    public Order changeOrderStatus(@PathVariable Long id){
        return orderService.changeOrderStatus(id);
    }
    
    @DeleteMapping("{id}")
    public Order deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }
}
