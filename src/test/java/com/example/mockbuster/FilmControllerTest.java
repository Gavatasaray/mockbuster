package com.example.mockbuster;

import com.example.mockbuster.api.web.controller.FilmContoller;
import com.example.mockbuster.api.web.service.impl.FilmServiceImpl;
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

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FilmControllerTest {

    @Autowired
    private FilmContoller filmController;

    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private MongoUtil mongoUtil;

    @Autowired
    private TestUtil testUtil;

    @Test
    public void testGetFilms() {
        MongoCollection<Document> films = testUtil.getCollection(Constants.FILM_COLLECTION);
        assertEquals(films.countDocuments(), filmController.getFilms().size());
    }

    @Test
    public void testEmptyCollection() {
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.FILM_COLLECTION)).thenReturn(null);
        assertNull(filmService.getFilms());
    }

    @Test
    public void testGetSingleFilm() {
        Document film = filmController.getFilm(107);
        Set<String> keys = film.keySet();
        assertTrue(keys.contains(Constants.CSTMRS_WHO_RENTED));
        assertNotNull(film.get(Constants.CSTMRS_WHO_RENTED));
    }

    @Test
    public void testGetSingleFilmNoCustomers() {

        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION))
                .thenReturn(null);
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.FILM_COLLECTION))
                .thenReturn(testUtil.getCollection(Constants.FILM_COLLECTION));

        Document film = filmService.getFilm(107);
        Set<String> keys = film.keySet();
        assertFalse(keys.contains(Constants.CSTMRS_WHO_RENTED));
    }

    @Test
    public void testSingleFilmNoFilmsInCollection() {
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.CUSTOMER_COLLECTION))
                .thenReturn(testUtil.getCollection(Constants.CUSTOMER_COLLECTION));
        when(mongoUtil.getCollection(Constants.ADMIN_DB, Constants.FILM_COLLECTION))
                .thenReturn(null);

        assertNull(filmService.getFilm(26));
    }

    @Test
    public void testGetSingleFilmDoesntExist() {
        assertNull(filmController.getFilm(0));
    }
}
