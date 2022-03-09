# Back-Fluent-English
This a Spring Boot REST API web service that is used for the [Fluent English](https://github.com/BiplopDey/Back-Fluent-English) web applications.

## Stack ##
- Java and Spring Boot using Maven
- Postman for API tests.

## Test
### Unit Test ###
Unit test in java, using Junit5 and Mockito
### Integration of API ###
Intregation test for API using Postman.
Copy this [link](https://www.getpostman.com/collections/5a93b173f31be9ec0aee)  of the collection and run it on Postman.



## Endpoints ##

### List of vocabularies ###

GET `/vocabularies`

Returns a list of vocabularies.

Optional query parameters:

- category:  phrasal-verb

### Get a single vocabulary ###

GET `/vocabularies/:vocabularyId`

Retrieve detailed information about a vocabulary.


### Submit your vocabulary ###

POST `/users/:userId/vocabularies`

Allows you to submit a new vocabulary. Requires authentication.

The request body needs to be in JSON format and include the following properties:

 - `name` - String - Required
 - `definition` - String - Required
 - `favorite` - Boolean - Optional

Example
```
POST /users/:userId/vocabularies
Authorization: Bearer <YOUR TOKEN>

{
  "name": Hello,
  "definition": "Greeting"
  "favorite": "true"
}
```

The response body will contain the vocabulary with Id.

### Get all your vocabularies ###

GET `/users/:userId/vocabularies`

Allows you to view all vocabularies. Requires authentication.

Optional query parameters:

- category:  phrasal-verb
- favorite: true or false
- 
### Get a vocabulary ###

GET `/users/:userId/vocabularies/:vocabularyId`

Allows you to view an existing vocabulary. Requires authentication.

### Update a vocabulary ###

PUT `/users/:userId/vocabularies/:vocabularyId`

Update an existing vocabulary. Requires authentication.

The request body needs to be in JSON format and allows you to update the following properties:

 - `name` - String - Required
 - `definition` - String - Required
 - `favorite` - Boolean - Optional

 Example
```
PUT /users/12/vocabularies/123
Authorization: Bearer <YOUR TOKEN>

{
  "name": Hello,
  "definition": "Greeting"
  "favorite": "true"
}
```

### Delete a vocabulary ###

DELETE `/users/:userId/vocabularies/:vocabularyId`

Delete an existing vocabulary. Requires authentication.

The request body needs to be empty.

 Example
```
DELETE /users/12/vocabularies/123
Authorization: Bearer <YOUR TOKEN>
```

## API Authentication ##

To submit or view your vocabulary, you need to register your API client.

POST `/users/register`

The request body needs to be in JSON format and include the following properties:

 - `name` - String
 - `email` - String

 Example

 ```
 {
    "name": "Example Name",
    "email": "Example@example.com"
}
 ```

The response body will contain the access token and your user ID.

Example
 ```
 {
    "accessToken": "OqumAgRkiCAOu8T6iziz",
    "id": "22"
}
 ```
