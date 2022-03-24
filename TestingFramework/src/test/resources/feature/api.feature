Feature: Validate API
    
   Scenario: Add a new pet to the store with available status
   	Given Endpoint to "postNewPet"
   	When User hits the POST Request with request body "PostNewPet.json"
   	Then Validate Response Status code: 200
   	And Fetch Pet ID
   
   Scenario: Find pet by ID
   	Given Endpoint to "getPetByID"
   	When User hits the GET Request
   	Then Validate Response Status code: 200
   	And Validate status value as "available" from response body
   	
   Scenario: Update an existing pet with sold status
   	Given Endpoint to "updatePet"
   	When User hits the PUT Request with request body "UpdatePet.json"
   	Then Validate Response Status code: 200
   	And Validate status value as "sold" from response body
  
   Scenario: Find pet by Status
   	Given Endpoint to "getPetByStatus"
   	When User hits the GET Request
   	Then Validate Response Status code: 200
   	   
   Scenario: Delete a pet
   	Given Endpoint to "deletePet"
   	When User hits the DELETE Request
   	Then Validate Response Status code: 200
   	And User hits the GET Request
   	Then Validate Response Status code: 404
 
   
   
   
