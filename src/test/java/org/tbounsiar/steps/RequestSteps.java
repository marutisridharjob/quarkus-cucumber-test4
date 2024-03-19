package org.tbounsiar.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Method;
import jakarta.inject.Inject;
import org.tbounsiar.cucumber.Context;
import org.tbounsiar.utils.DataFile;

public class RequestSteps {

    @Inject
    Context context;

    @When("Send the request url {string} with the method {method}")
    public void requestMethodWithUrl(String url, Method method) {
        context.method = method;
        context.url = url;
    }

    @And("The request body is {jsonFile}")
    public void requestWithJsonBody(DataFile jsonFile) {
        context.request().contentType(ContentType.JSON);
        context.request().body(jsonFile.getContent());
    }

    @And("The Request Parameter {string} Is {string}")
    public void requestParam(String name, String value) {
        context.request().params(name, value);
    }

    @And("The Request Params Are {jsonFile}")
    public void requestParams(DataFile jsonFile) {
        context.request().params(jsonFile.getMap());
    }

    @And("The Request Header {string} Is {string}")
    public void requestHeader(String name, String value) {
        context.request().header(new Header(name, value));
    }

    @And("The Request Headers Are {jsonFile}")
    public void requestHeaders(DataFile jsonFile) {
        context.request().headers(jsonFile.getMap());
    }
}
