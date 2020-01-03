package com.example.mockbuster.api.web.controller;

import com.example.mockbuster.api.web.service.CustomerService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ArrayList<Document> getCustomers() {

        return customerService.getCustomers();
    }

    @GetMapping("/customer")
    public Document getCustomer(@RequestParam int id) {
        return customerService.getCustomer(id);
    }
}
