package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class CodeReview {

    @Given("Get pull request")
    public void getPullRequest() {
        RestAssured.baseURI = "github-emulator-host:port";
        given().urlEncodingEnabled(true)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .get("/webhook/github/pr")
                .then().statusCode(201);
    }

    @When("Pull Request Action is Invalid")
    public void pullRequestActionIsInvalid() {
        RestAssured.baseURI = "github-emulator-host:port";
        String response = given().urlEncodingEnabled(true)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .get("/webhook/github/pr")
                .body().toString();
        response.contains("\"action\" : \"labeled\"");
    }

    @Then("Peer Review Result status is FAIL")
    public void peerReviewResultStatusIsFAIL() {
        Assert.fail();
    }
}

