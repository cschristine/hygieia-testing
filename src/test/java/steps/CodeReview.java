package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class CodeReview {

    // Temporary local Test execution setup
    private String githubEmulatorHost = "http://localhost:8089";

    @Given("Get pull request with labeled action")
    public void getPullRequest() {
        given().urlEncodingEnabled(true)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .get(githubEmulatorHost+"/webhook/github/pr")
                .then().statusCode(201);
    }

    @When("Pull Request invalid action identified")
    public void pullRequestActionIsInvalid() {
        String invalidActionId = "\"action\":\"labeled\"";
        ResponseBody responseBody = given().urlEncodingEnabled(true)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .get(githubEmulatorHost+"/webhook/github/pr")
                .body();
        Assert.assertTrue(responseBody.asString().contains(invalidActionId));
    }

    @Then("Peer Review Result status is FAIL")
    public void peerReviewResultStatusIsFAIL() {
        Assert.fail();
    }

    @Then("TestCase is PASS")
    public void testcaseIsPASS() {
        Assert.assertTrue(true);
    }
}

