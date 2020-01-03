package com.example.mockbuster.api.web.controller;

import com.example.mockbuster.api.web.service.FilmService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FilmContoller {

    @Autowired
    private FilmService filmService;

    @GetMapping("/films")
    public ArrayList<Document> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/film")
    public Document getFilm(@RequestParam int id) {
        return filmService.getFilm(id);
    }
}
