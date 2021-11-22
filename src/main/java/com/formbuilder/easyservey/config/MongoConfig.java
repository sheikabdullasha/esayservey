package com.formbuilder.easyservey.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {


   /* ConnectionString connectionString = new ConnectionString("mongodb+srv://sheik:abuabu@sheikcluster.cwbuj.mongodb.net/mocsyam?retryWrites=true&w=majority");
    MongoClientSettings setting = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(setting);
    MongoDatabase database = mongoClient.getDatabase("mocsyam");

    MongoCollection<Document> userCollection=database.getCollection("user");*/

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://sheik:abuabu@cluster0.0uz8r.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }


    @Override
    protected String getDatabaseName() {
        return "myFirstDatabase";
    }
}
