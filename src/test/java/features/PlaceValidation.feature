Feature: Validating Place APIs

@AddPlace @Regression
Scenario Outline: Verify Place is successfully added with AddPlace API

Given AddPlace payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "post" http method
Then Http Status Code is 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
|name   | language | address      |
|Valerie| English  | 54 Park Drive|
#|Lis    | Spanish  | 26 Sais Ave  |

 @DeletePlace @Regression
 Scenario: Verify DeletePlace API functionality is working
 
 Given DeletePlace payload 
 When User calls "DeletePlaceAPI" with "post" http method
 Then Http Status Code is 200
 And "status" in response body is "OK"
 