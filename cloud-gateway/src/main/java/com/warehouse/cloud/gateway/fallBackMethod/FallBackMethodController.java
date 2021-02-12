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
}
