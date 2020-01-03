package com.example.mockbuster.api.web.service;

import org.bson.Document;

import java.util.ArrayList;

public interface CustomerService {

    ArrayList<Document> getCustomers();

    Document getCustomer(int id);
}
