package com.freenow.blog.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import com.freenow.blog.Utilities.PropertyReader;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ContactList extends PropertyReader
{
    String server = LoadProperties().getProperty("server");
    public Response response;
    String APIBody = "{}";
    private RequestSpecification request;
    static String _id;

    @Given("the endpoints exists")
    public void theEndpointsExists()
    {
        request = given();
    }

   @When("I perform Get Operation for {string}")
    public void i_perform_Get_Operation_for(String endpoint)
    {
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(APIBody)
                .get(server+endpoint);
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int code)
    {
        Assert.assertEquals(code, response.getStatusCode());
    }

  @And("I should see the Emp_company name as {string}")
    public void i_should_see_the_Emp_company_name_as(String empComp)
    {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empComp), true);
       // Assert.assertEquals(pojo.getName(), name);
    }

    /*@When("I perform Post Operation for {string}")  //function with Json payload in Pojo
    public void iPerformPostOperationFor(String endpoint) throws JsonProcessingException
    {
        //File jsonDataInFile = new File("src/test/resources/schema/user_posts.json");
        pojo.setName("Talha");
        ObjectMapper objectMapper = new ObjectMapper();

        String userJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
        System.out.println("here "+ userJson);

        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(userJson)
                .post(server+endpoint)
                .then().extract().response();

        System.out.println(server+endpoint);
    }*/

    @When("I perform Post Operation for {string}")   //function with Json payload in file
    public void iPerformPostOperationFor(String endpoint)
    {
        File jsonDataInFile = new File("src/main/resources/schema/Post_Contact.json");
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(jsonDataInFile)
                .post(server+endpoint)
                .then().extract().response();
        _id = response.jsonPath().getString("_id"); //check here
        System.out.println("id here is "+_id);
    }

    @And("I should see the email posted as {string}")
    public void iShouldSeeTheEmailPostedAs(String email)
    {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(email), true);

    }

    @When("I perform Get Operation after Post for {string}")
    public void iPerformGetOperationAfterPostFor(String endpoint)
    {
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(APIBody)
                .get(server+endpoint+_id);
        //System.out.println(server+endpoint+_id);
    }

    @And("I should Get the Posted email as {string}")
    public void iShouldGetThePostedEmailAs(String email)
    {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(email), true);
    }

    @When("I perform PUT Operation for {string}")
    public void iPerformPUTOperationFor(String endpoint)
    {
        File jsonDataInFile = new File("src/main/resources/schema/Put_Contact.json"); //make file placement elsewhere in later phase
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(jsonDataInFile)
                .put(server+endpoint+_id);
    }

    @Then("the status code after PUT should be {int}")
    public void theStatusCodeAfterPUTShouldBe(int code)
    {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @And("I perform Get for {string} after Post to see Company PUT as {string}")
    public void iPerformGetForAfterPostToSeeCompanyPUTAs(String endpoint, String jobTitle)
    {
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(APIBody)
                .get(server+endpoint+_id);
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(jobTitle), true);
    }

    @When("I perform Delete Operation for {string}")
    public void iPerformDeleteOperationFor(String endpoint)
    {
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(APIBody)
                .delete(server+endpoint+_id);
    }

    @Then("the status code after Delete should be {int}")
    public void theStatusCodeAfterDeleteShouldBe(int code)
    {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @And("I perform Get for {string} after Delete to see statusCode as {int}")
    public void iPerformGetForAfterDeleteToSeeStatusCodeAs(String endpoint, int code)
    {
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(APIBody)
                .get(server+endpoint+_id);
        Assert.assertEquals(code, response.getStatusCode());
    }
}
