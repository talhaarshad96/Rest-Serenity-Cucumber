package com.freenow.blog.stepdefinitions;
//package stepdefinitions;


import com.freenow.blog.Models.Pojo;
import com.freenow.blog.Utilities.PropertyReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.yecht.IOType.File;

public class OtherSteps extends PropertyReader
{
    // String server = PropertyReader().getProperty("server");
    String server = LoadProperties().getProperty("server");

    private String path;
    public Response response;
    String APIBody = "{}";
    // public static String BASE_URL = "https://reqres.in"; //api/users?=page=2";
    public ValidatableResponse json;

    Pojo pojo = new Pojo();
    private RequestSpecification request;

    @Given("the user endpoint exists")
    public void theUserEndpointExists()
    {
        request = given();
    }

   /*@When("I perform Get Operation for {string}")
    public void iPerformGetOperationFor(String endpoint)
    {
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(APIBody)
                .get(server+endpoint)
                .then().extract().response();

        System.out.println(server+endpoint);
    }*/

   /* @Then("status code should be {int}")
    public void statusCodeShouldBe(int code)
    {
        Assert.assertEquals(code, response.getStatusCode());
    }*/

    @And("I should see the first_name name as {string}")
    public void iShouldSeeTheFirst_nameNameAs(String name)
    {
        String responseBody = response.getBody().asString();
        // Assert.assertEquals(responseBody.contains("Michael"), true);
        Assert.assertEquals(responseBody.contains(name), true);
    }


    @Given("the post endpoint exists")
    public void thePostEndpointExists()
    {
        request = given();
    }

    /*@When("I perform Post Operation for {string}")   //function with Json payload in file
    public void iPerformPostOperationFor(String endpoint)
    {
        File jsonDataInFile = new File("src/test/resources/schema/user_posts.json");
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(jsonDataInFile)
                .post(BASE_URL+endpoint)
                .then().extract().response();

        System.out.println(BASE_URL+endpoint);
    }*/

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

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int code)
    {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @And("I should see the name posted as {string}")
    public void iShouldSeeTheNamePostedAs(String name)
    {
        String responseBody = response.getBody().asString();
        // Assert.assertEquals(responseBody.contains("Michael"), true);
        //Assert.assertEquals(responseBody.contains(name), true);
        Assert.assertEquals(pojo.getName(), name);
    }


}

