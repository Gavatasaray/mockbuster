package com.example.mockbuster;

import com.example.mockbuster.api.web.controller.CustomerController;
import com.example.mockbuster.api.web.service.impl.CustomerServiceImpl;
import com.example.mockbuster.utils.Constants;
import com.example.mockbuster.utils.MongoUtil;
import com.example.mockbuster.utils.TestUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private MongoUtil mongoUtil;

    @Autowired
    private TestUtil testUtil;

    @Test
    public void testCustomerList() {
        MongoCollection<Document> customers = testUtil.getCollection(Constants.CUSTOMER_COLLECTION);
        assertEquals(customers.countDocuments(), customerController.getCustomers().size());
    }

    @Test
    public void testGetSingleCustomer() {
        Document doc = customerController.getCustomer(7);
        assertEquals(7, doc.get(Constants.ID));
    }

    @Test
    public void testGetSingleCustomerEmptyCollection() {
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION)).thenReturn(null);
        assertNull(customerService.getCustomer(1000));
    }

    @Test
    public void testGetSingleCustomerIdDoesntExist() {
        assertNull(customerController.getCustomer(1000));
    }

    @Test
    public void testEmptyCollection() {
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION)).thenReturn(null);
        assertNull(customerService.getCustomers());
    }

    @Test
    public void testNoFilms() {
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION))
                .thenReturn(testUtil.getCollection(Constants.CUSTOMER_COLLECTION));
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.FILM_COLLECTION))
                .thenReturn(null);

        Document result = customerService.getCustomer(1);
        ArrayList<Document> rentals = (ArrayList<Document>) result.get(Constants.RENTALS);
        Set<String> keys = rentals.get(0).keySet();
        assertFalse(keys.contains(Constants.ACTORS));
    }
}
