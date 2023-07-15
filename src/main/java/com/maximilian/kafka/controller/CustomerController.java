package com.maximilian.kafka.controller;

import com.maximilian.kafka.model.Customer;
import com.maximilian.kafka.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> test(@RequestParam String name) {
        Customer customer = new Customer(1L, name, new Customer(2L, name + "_parent", null));
        customerService.save(customer);
        return ResponseEntity.noContent().build();
    }

}
