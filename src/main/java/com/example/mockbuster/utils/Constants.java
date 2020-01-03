package com.example.mockbuster.utils;

import java.util.ArrayList;

public class Constants {

    public static final String FILM_COLLECTION = "DVDRentals-films";
    public static final String CUSTOMER_COLLECTION = "DVDRentals-customers";
    public static final String ID = "_id";
    public static final String TITLE = "Title";
    public static final String CATEGORY = "Category";
    public static final String DESCRIPTION = "Description";
    public static final String RATING = "Rating";
    public static final String RENTAL_DURATION = "Rental Duration";
    public static final String RENTALS = "Rentals";
    public static final String FILM_ID = "filmId";
    public static final String CSTMRS_WHO_RENTED = "Customers who rented";
    public static final String LENGTH = "Length";
    public static final String REPLACEMENT_COST = "Replacement Cost";
    public static final String SPECIAL_FEATURES = "Special Features";
    public static final String ACTORS = "Actors";

    public static final ArrayList<String> REFINED_FILM_DETAILS = new ArrayList<String>() {{
        add(ID); add(TITLE); add(CATEGORY); add(DESCRIPTION); add(RATING); add(RENTAL_DURATION);
    }};

    public static final ArrayList<String> CUSTOMER_FILM_DETAILS = new ArrayList<String>() {{
        add(ACTORS); add(CATEGORY); add(DESCRIPTION); add(RATING);
        add(RENTAL_DURATION); add(LENGTH); add(REPLACEMENT_COST); add(SPECIAL_FEATURES);
    }};

    public static final String ADMIN_DB = "admin";
}
