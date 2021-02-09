package com.warehouse.order.service;

import com.warehouse.order.common.RequestTemplate;
import com.warehouse.order.common.ResponseTemplate;
import com.warehouse.order.common.models.Client;
import com.warehouse.order.entity.Order;
import com.warehouse.order.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate){
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        if (order.getOrderId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order cannot have id");
        return  orderRepository.save(order);
    }

    public ResponseTemplate getOrderWithDetailsById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot  find order with provided id"));
        Client client = restTemplate.getForObject("http://CLIENT-SERVICE/api/client/"+order.getClientId(), Client.class);
        RequestTemplate requestTemplate = restTemplate.getForObject("http://PRODUCT-SERVICE/api/product/"+order.getProductId(),
                RequestTemplate.class);
        return new ResponseTemplate(order.getOrderId(), client, requestTemplate.getProduct(), order.getDate(), order.isActive());
    }
}
