##fix the Given and others that are repeated.
#latter make the GIVEN (the endpoint exists main actual url hit karo with endpoint or something else)

Feature: To use get-post-update-delete

  Scenario: using the Get_contactList
    Given the endpoints exists
    When I perform Get Operation for "/contacts"
    Then status code should be 200
    And I should see the Emp_company name as "Apple"

  Scenario: using POST_ToaddContact
    Given the endpoints exists
    When I perform Post Operation for "/contacts"
    Then status code should be 200
    And I should see the email posted as "talha321@thinkingtester.com"

  Scenario: using Get_contactList after PostContact using its ID
    Given the endpoints exists
    When I perform Get Operation after Post for "/contacts/"
    Then status code should be 200
    And I should Get the Posted email as "talha321@thinkingtester.com"

  Scenario: using PUT_ContactList
    Given the endpoints exists
    When I perform PUT Operation for "/contacts/"
    Then the status code after PUT should be 204
    And I perform Get for "/contacts/" after Post to see Company PUT as "Nisumumum"

  Scenario: using Delete_ContactList
    Given the endpoints exists
    When I perform Delete Operation for "/contacts/"
    Then the status code after Delete should be 204
    And I perform Get for "/contacts/" after Delete to see statusCode as 404

  Scenario Outline: User can POST the user data
    Given the endpoints exists
    When I perform POST Operation having Data <firstName> and <lastName> and <email> and <city> and <country> and <jobTitle> and <company>
    Then status code should be 200
    And User is updated  with <firstName> and <lastName> and <email> and <city> and <country> and <jobTitle> and <company>

    Examples:
      | firstName  | lastName | email                  | city    | country | jobTitle                 | company |
      | Talha      | Arshad   | talha.arshad@gmail.com | Karachi | Pak     | Automation               | Nisum   |
      | Vijay      | Khatri   | vijay.khatri@gmail.com | Karachi | Pak     | Software Engineer        | Nisum   |
      | Inam       | Ullah    | inam.ullah@gmail.com   | Karachi | Pak     | Senior Software Engineer | Nisum   |


  #FAZOOL STUFF

#Feature: POSTCallReadingDataFromCSVFile
#
#  Background:
#    * url 'http://3.13.86.142:3000'
#
#  Scenario Outline: create a user from given details.
#
#    Given path '/contacts'
#    And request {firstName:<firstName>, lastName:<lastName>,email:<email>}
#    When method post
#    Then status 200
#    Then print response
#
#    And match $.Status == '#present'
#    And match $.Status == 'OK'
#
#    Examples:
#      | read('src/test/java/Utilities/data.csv') |
