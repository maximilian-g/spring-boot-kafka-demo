package com.maximilian.kafka.controller;

import com.github.javafaker.Faker;
import com.maximilian.kafka.model.Customer;
import com.maximilian.kafka.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> test() {
        Faker faker = new Faker();
        for (int i = 0; i < 1000; i++) {
            Customer customer = new Customer(i, faker.name().fullName(),
                    new Customer((i + 1) + 1000, faker.name().fullName(), null));
            customerService.save(customer);
        }
        return ResponseEntity.noContent().build();
    }

}
