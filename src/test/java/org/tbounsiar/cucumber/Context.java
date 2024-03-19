package org.tbounsiar.cucumber;

import com.mongodb.client.MongoClient;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Singleton;

import static io.restassured.RestAssured.given;


@Singleton
public class Context {

    public static MongoDb mongoDb = new MongoDb();

    private ValidatableResponse response;
    private RequestSpecification request;
    public Method method;
    public String url;

    public MongoClient client() {
        return mongoDb.getClient();
    }

    public RequestSpecification request() {
        return request;
    }

    public ValidatableResponse response() {
        if (response == null) {
            response = request.request(method, url).then();
        }
        return response;
    }

    public void init() {
        request = given().when();
        response = null;
        method = null;
        url = null;
    }
}
