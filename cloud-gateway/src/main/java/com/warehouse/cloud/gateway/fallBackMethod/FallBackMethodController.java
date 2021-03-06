package com.warehouse.cloud.gateway.fallBackMethod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/addressServiceFallBack")
    public String addressServiceFallBackMethod(){
        return "Address Service is taking longer than expected"
                +"please try again later";
    }

    @GetMapping("/warehouseServiceFallBack")
    public String warehouseServiceFallBackMethod(){
        return "Warehouse Service is taking longer than expected"
                +"please try again later";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBackMethod(){
        return "Product Service is taking longer than expected"
                +"please try again later";
    }

    @GetMapping("categoryServiceFallBack")
    public String categoryServiceFallBackMethod(){
        return "Category Service is taking longer than expected"
                +"please try again later";
    }

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBackMethod(){
        return "Order Service is taking longer than expected"
                +"please try again later";
    }

    @GetMapping("/clientServiceFallBack")
    public String clientServiceFallBackMethod(){
        return "Client Service is taking longer than expected"
                +"please try again later";
    }
}
