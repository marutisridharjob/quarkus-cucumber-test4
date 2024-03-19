package org.tbounsiar.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.ParameterType;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tbounsiar.cucumber.Context;
import org.tbounsiar.utils.DataFile;

@ApplicationScoped
public class CommonSteps {

    @Inject
    Context context;

    @BeforeAll
    public static void beforeAll() {
        Context.mongoDb.start();
    }

    @AfterAll
    public static void afterAll() {
        Context.mongoDb.stop();
    }

    @Before
    public void before() {
        context.init();
    }

    @ParameterType(".*\\.json")
    public DataFile jsonFile(String path) {
        return new DataFile(path, DataFile.Type.JSON);
    }

    @ParameterType(".*\\.csv")
    public DataFile csvFile(String path) {
        return new DataFile(path, DataFile.Type.CSV);
    }

    @ParameterType("ANY|TEXT|JSON|XML|HTML|URLENC|BINARY|MULTIPART")
    public ContentType contentType(String contentType) {
        return ContentType.valueOf(contentType);
    }

    @ParameterType("GET|PUT|POST|DELETE|HEAD|TRACE|OPTIONS|PATCH")
    public Method method(String method) {
        return Method.valueOf(method);
    }
}
