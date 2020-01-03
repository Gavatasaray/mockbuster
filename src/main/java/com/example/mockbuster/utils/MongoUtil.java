package com.example.mockbuster.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class MongoUtil {

    public MongoCollection<Document> getCollection(String dbName, String collectionName) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase db = mongoClient.getDatabase(dbName);
		return db.getCollection(collectionName);
    }

    private MongoUtil() {

    }
}
