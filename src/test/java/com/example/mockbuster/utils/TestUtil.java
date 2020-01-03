package com.example.mockbuster.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class TestUtil {

    public MongoCollection<Document> getCollection(String collection) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase db = mongoClient.getDatabase(Constants.ADMIN_DB);

        return db.getCollection(collection);
    }

}
