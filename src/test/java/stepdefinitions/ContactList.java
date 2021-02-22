package stepdefinitions;

import Models.Pojo;
import Models.PojoLocation;
import Utilities.DataReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import Utilities.PropertyReader;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ContactList extends PropertyReader
{
    String server = LoadProperties().getProperty("server");
    String contact = LoadProperties().getProperty("contact");
    public Response response;
    String APIBody = "{}";
    private RequestSpecification request;
    static String _id;

    Pojo pojo = new Pojo();
    DataReader dataReader = new DataReader();

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
        pojo.setFirstName("Talha");
        pojo.setLastName("Arshad");
        pojo.setEmail("talha@nisum.com");
        pojo.getLocation().setCity("Karachi");
        pojo.getLocation().setCountry("Pakistan");
        pojo.getEmployer().setJobTitle("Automation Engineer");
        pojo.getEmployer().setCompany("Nisum Pakistan");
        ObjectMapper objectMapper = new ObjectMapper();

        String userJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
        System.out.println("here "+ userJson);

        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(userJson)
                .post(server+endpoint)
                .then().extract().response();
        _id = response.jsonPath().getString("_id"); //check here
        System.out.println("id here is "+_id);
        System.out.println(server+endpoint);
    }*/

    /*@When("I perform Post Operation for {string}")   //function with Json payload in file
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
    }*/

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
        System.out.println(server+endpoint+_id);
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

            //function with Json payload in Pojo & Data From DataBuilder
    @When("I perform POST Operation having Data ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)")
    public void iPerformPOSTOperationHavingDataFirstNameAndLastNameAndEmailAndCityAndCountryAndJobTitleAndCompany(String firstName, String lastName, String email,String city,String country,String jobTitle, String company)
    {
        response = request.when()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(dataReader.addUserPayLoad(firstName, lastName, email, city, country, jobTitle, company))
                .post(server+contact)
                .then().extract().response();
        _id = response.jsonPath().getString("_id"); //check here
        System.out.println("id here is "+_id);

    }

    @And("User is updated  with ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)")
    public void userIsUpdatedWithFirstNameAndLastNameAndEmailAndCityAndCountryAndJobTitleAndCompany(String firstName, String lastName, String email,String city,String country,String jobTitle, String company)
    {
        String responseBody = response.getBody().asString();
       // Assert.assertEquals(responseBody.contains(empComp), true);
        Assert.assertEquals(firstName, dataReader.getPojo().getFirstName());
        Assert.assertEquals(lastName, dataReader.getPojo().getLastName());
        Assert.assertEquals(email, dataReader.getPojo().getEmail());
        Assert.assertEquals(city, dataReader.getPojo().getLocation().getCity());
        Assert.assertEquals(country, dataReader.getPojo().getLocation().getCountry());
        Assert.assertEquals(jobTitle, dataReader.getPojo().getEmployer().getJobTitle());
        Assert.assertEquals(company, dataReader.getPojo().getEmployer().getCompany());

        System.out.println("Response after user update is :  " + response.asString());
    }

}
