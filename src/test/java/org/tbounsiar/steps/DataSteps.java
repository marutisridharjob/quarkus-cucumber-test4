package org.tbounsiar.steps;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import jakarta.inject.Inject;
import org.bson.Document;
import org.tbounsiar.cucumber.Context;
import org.tbounsiar.utils.DataFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataSteps {

    @Inject
    Context context;

    @Given("The collection {string} exists")
    public void theCollectionExists(String collectionName) {
        context.client().getDatabase("quarkus").getCollection(collectionName).drop();
        context.client().getDatabase("quarkus").createCollection(collectionName);
    }

    @Given("The collection {string} contains data from {csvFile}")
    public void theCollectionContainsDataFromJsonFile(String collectionName, DataFile csvFile) {
        MongoCollection<Document> collection = context.client().getDatabase("quarkus").getCollection(collectionName);
        var documents = csvFile.get();
        collection.insertMany(documents);
    }

    @Then("Expect the collection {string} to contains data from {csvFile}")
    public void expectTheCollectionToContainsDataFromCsvFile(String collectionName, DataFile csvFile) {
        FindIterable<Document> content = context.client().getDatabase("quarkus").getCollection(collectionName).find();
        List<Document> documents = csvFile.get();
        List<Document> saved = new ArrayList<>();
        content.forEach(saved::add);
        assertTrue(saved.containsAll(documents));
    }

    @Then("Expect the collection {string} to not contains data from {csvFile}")
    public void expectTheCollectionToNotContainsDataFromCsvFile(String collectionName, DataFile csvFile) {
        FindIterable<Document> content = context.client().getDatabase("quarkus").getCollection(collectionName).find();
        List<Document> documents = csvFile.get();
        List<Document> saved = new ArrayList<>();
        content.forEach(saved::add);
        assertFalse(saved.containsAll(documents));
    }
}
