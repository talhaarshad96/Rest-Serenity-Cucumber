##fix the Given and others that are repeated.
#latter make the GIVEN (the endpoint exists main actual url hit karo with endpoint or something else)

Feature: To use get-post-update-delete

  #Scenario: using the Get_contactList
  #  Given the endpoints exists
   # When I perform Get Operation for "/contacts"
   # Then status code should be 200
   # And I should see the Emp_company name as "Kforce"

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

 # Scenario: using PUT_ContactList
 #   Given the endpoint exists
  #  When I perform PUT Operation for "/contacts"
 #   Then the status code should be 200
  #  And I should see the name posted as "Talha"