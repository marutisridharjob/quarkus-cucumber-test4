package org.tbounsiar.cucumber;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import org.testcontainers.containers.GenericContainer;

import java.util.List;

public class MongoDb {

    @Getter
    private MongoClient client;
    private GenericContainer container = new GenericContainer<>("mongo:6.0.14-jammy");

    public void start() {

        container.withExposedPorts(27017);
        container.setPortBindings(List.of("27017:27017"));
        container.start();
        client = MongoClients.create("mongodb://localhost:27017");
    }

    public void stop() {

        client.close();
        container.stop();
        container.close();
    }
}
