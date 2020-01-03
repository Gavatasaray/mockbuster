package com.example.mockbuster.api.web.service;

import org.bson.Document;

import java.util.ArrayList;

public interface FilmService {

    ArrayList<Document> getFilms();

    Document getFilm(int id);
}
