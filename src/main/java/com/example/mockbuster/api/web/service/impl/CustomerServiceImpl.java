package com.example.mockbuster.api.web.service.impl;

import com.example.mockbuster.api.web.service.CustomerService;
import com.example.mockbuster.utils.Constants;
import com.example.mockbuster.utils.MongoUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private MongoUtil mongoUtil;

    @Override
    public ArrayList<Document> getCustomers() {
        MongoCollection<Document> customers = mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION);

        if(null == customers) {
            return null;
        } else {
            FindIterable<Document> fi = customers.find();
            MongoCursor<Document> cursor = fi.iterator();

            ArrayList<Document> entries = new ArrayList<>();

            while(cursor.hasNext()) {
                entries.add(cursor.next());
            }
            cursor.close();

            return entries;
        }
    }

    @Override
    public Document getCustomer(int id) {
        MongoCollection<Document> customers = mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION);
        if(null == customers) {
            return null;
        } else {
            Document customer = customers.find(eq(Constants.ID, id)).first();
            ArrayList<Document> rentalDetails = new ArrayList<>();
            if(null != customer) {
                ArrayList<Document> rentals = (ArrayList<Document>)customer.get(Constants.RENTALS);
                Document film;
                MongoCollection<Document> films = mongoUtil.getCollection(Constants.ADMIN_DB, Constants.FILM_COLLECTION);
                if(null != films) {
                    for (Document rentedFilm : rentals) {
                        film = films.find(eq(Constants.ID, rentedFilm.get(Constants.FILM_ID))).first();
                        if(null != film) {
                            for (String detail : Constants.CUSTOMER_FILM_DETAILS) {
                                rentedFilm.put(detail, film.get(detail));
                            }
                            rentalDetails.add(rentedFilm);
                        }
                    }
                    customer.put(Constants.RENTALS, rentalDetails);
                }
            }
            return customer;
        }
    }
}
