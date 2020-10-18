 Feature: Validation of REST API for DEMO PET STORE

@GetPets
Scenario Outline: Fetch the Pets based on Status
	Given Create Gets Pets Payload with "<Status>"
	When User calls "FindByStatusAPI" of Demo Pet Store
	Then User verify "<Status>" of all the Pets in response
	
	Examples:
		|Status  	|
		|available 	|  


@NewPet
Scenario Outline: Add new Pets to Demo Pet Store
	Given Create New Pet Payload with "<Id>" "<Name>" "<Status>"
	When User calls "AddNewPetAPI" of Demo Pet Store
	Then User verify "<Id>" "<Name>" "<Status>" of the Pet in response
	
	Examples:
		|Id 	| Name 		|	Status		|
		|25 	| Green 	|	available	|


@UpdatePet
Scenario: Update existing Pet in Demo Pet Store
	Given Create update Pet Payload
	When User calls "UpdatePetAPI" of Demo Pet Store
	Then User verify Message response


@DeletePet
Scenario: Delete existing Pet in Demo Pet Store
	Given Create delete Pet Payload
	When User calls "DeletePetAPI" of Demo Pet Store
	Then User verify Message response
	
			

	
	
	
	
	
	

	
	
	