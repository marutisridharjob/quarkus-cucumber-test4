package org.tbounsiar.steps;

import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.tbounsiar.cucumber.Context;
import org.tbounsiar.utils.DataFile;

public class ResponseSteps {

    @Inject
    Context context;

    @Then("Expect the response status code to be: {int}")
    public void expectHttpStatus(int status) {
        context.response().statusCode(status);
    }

    @Then("Expect the response body to be: {jsonFile}")
    public void expectBodyToBeJson(DataFile jsonFile) throws JSONException {
        context.response().contentType(ContentType.JSON);
        JSONAssert.assertEquals(jsonFile.getContent(), context.response().extract().body().asString(), false);
    }

    @Then("Expect the response content type to be: {contentType}")
    public void expectContentTypeToBe(ContentType contentType) {
        context.response().contentType(contentType);
    }

    @Then("Expect the response header {string} to be: {string}")
    public void expectHeaderToBe(String key, String value) {
        context.response().header(key, value);
    }
}
