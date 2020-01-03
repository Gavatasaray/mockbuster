package com.example.mockbuster.api.web.service.impl;

import com.example.mockbuster.api.web.service.FilmService;
import com.example.mockbuster.utils.Constants;
import com.example.mockbuster.utils.MongoUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private MongoUtil mongoUtil;

    @Override
    public ArrayList<Document> getFilms() {
        MongoCollection<Document> films = mongoUtil.getCollection(Constants.ADMIN_DB, Constants.FILM_COLLECTION);
        return null != films ? createFilmList(films) : null;
    }

    @Override
    public Document getFilm(@PathVariable int id) {
        MongoCollection<Document> customers = mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION);
        MongoCollection<Document> films = mongoUtil.getCollection(Constants.ADMIN_DB, Constants.FILM_COLLECTION);
        Document film;

        film = findFilm(films, id);

        if(null == film) {
            return null;
        }

        if(null == customers) {
            return film;
        } else {
            return updateFilmDetailsWithCustomers(customers, id, film);
        }
    }

    private ArrayList<Document> createFilmList(MongoCollection<Document> films) {
        ArrayList<Document> filmsList = new ArrayList<>();

        FindIterable<Document> fi = films.find();
        MongoCursor<Document> cursor = fi.iterator();
        while(cursor.hasNext()) {
            filmsList.add(populateFilmDetails(cursor.next()));
        }
        cursor.close();
        return filmsList;
    }

    private Document populateFilmDetails(Document doc) {
        Document film = new Document();
        for(String detail : Constants.REFINED_FILM_DETAILS) {
            film.put(detail, doc.get(detail));
        }
        return film;
    }

    private Document updateFilmDetailsWithCustomers(MongoCollection<Document> customers, int id, Document film) {
        FindIterable<Document> fi = customers.find();
        MongoCursor<Document> cursor = fi.iterator();
        ArrayList<Integer> customersWhoRented = new ArrayList<>();
        while(cursor.hasNext()) {
            Document current = cursor.next();
            ArrayList<Document> rentals = (ArrayList<Document>)current.get(Constants.RENTALS);
            for(Document rental : rentals) {
                if((int) rental.get(Constants.FILM_ID) == id) {
                    customersWhoRented.add((Integer) current.get(Constants.ID));
                    film.put(Constants.CSTMRS_WHO_RENTED, customersWhoRented);
                }
            }
        }
        return film;
    }

    private Document findFilm(MongoCollection<Document> films, int id) {
        if(null != films) {
            return films.find(eq(Constants.ID, id)).first();
        }
        return null;
    }
}
