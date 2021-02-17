package com.freenow.blog.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.runtime.Property;
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
                .get(server+endpoint)
                .then().extract().response();

        System.out.println(server+endpoint);
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int code)
    {
        Assert.assertEquals(code, response.getStatusCode());
    }

   @And("I should see the Emp_company name as {string}")
    public void i_should_see_the_Emp_company_name_as(String empComp)
    {
      //  ResponseBody body = response.getBody();
      //  System.out.println("printing here "+body.asString());
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
        String _id = response.jsonPath().getString("_id"); //check here
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(APIBody)
                .get(server+endpoint+_id)
                .then().extract().response();

        System.out.println(server+endpoint+_id);
    }

    @And("I should Get the Posted email as {string}")
    public void iShouldGetThePostedEmailAs(String arg0)
    {

    }
}
