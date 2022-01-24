Feature: Validating Place API's
@AddPlace
Scenario Outline:: Verify if place is being successfully added by AddPlaceApi
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceApi" with "POST" http request
	Then The Api call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceApi"
	
Examples:
	|name	|language	|address				   |
	|Manish	|Hindi		|39, Shivlok colony, Bhopal|
#	|Garima	|Marwari	|A101, Pune				   |
	
@DeletePlace
Scenario: Verify if delete functionality is working
	Given DeletePlacePayload
	When User calls "DeletePlaceApi" with "POST" http request
	Then The Api call is success with status code 200
	And "status" in response body is "OK"