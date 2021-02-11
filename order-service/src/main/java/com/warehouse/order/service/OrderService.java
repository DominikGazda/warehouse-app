package com.warehouse.order.service;

import com.warehouse.order.common.RequestTemplate;
import com.warehouse.order.common.ResponseTemplate;
import com.warehouse.order.common.models.Client;
import com.warehouse.order.entity.Order;
import com.warehouse.order.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
        order.setActive(true);
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


    public Order updateOrder(Order order, Long id) {
        if(!order.getOrderId().equals(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Order id must be same as id in path variable");
        Order oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Cannot find order with provided id"));
        return orderRepository.save(order);
    }

    public Order changeOrderStatus(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot  find order with provided id"));
        order.setActive(!order.isActive());
        return orderRepository.save(order);
    }

    public Order deleteOrder(Long id) {
        Order orderToDelete = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Cannot find order with provided id"));
        orderRepository.delete(orderToDelete);
        return orderToDelete;
    }

    public void createErrorsMessage(BindingResult errors){
        List<ObjectError> errorList = errors.getAllErrors();
        String message = errorList.stream()
                .map(ObjectError::getDefaultMessage)
                .map(err -> err +" ")
                .map(String::toString)
                .collect(Collectors.joining());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
    }


}
