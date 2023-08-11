# assignment-menagerie
Menagerie Database APIs: A Spring Boot project providing RESTful APIs to manage a 'Menagerie' database, allowing CRUD operations for pets and their events. Implements sorting, error handling, and logging.

1. List all Pets - GET /pets : The API should return a list of all pets in the pets table. Optionally,
the API should be able to take the request parameter species and use it to return a list of
selected species.

2. Get a single Pet and all of its events - GET /pets/{id} : The API should return the pet
identified by {id} and all of its events, showing the latest event first. Optionally, the API
should be able to accept the sorting key and order in request parameters.

3. Add a Pet - POST /pets : The API should create a pet entry and return it. The API must return
an appropriate error for any constraint violation.

4. Edit a Pet - PUT /pets : The API should update the pet identified by {id} using the user's
input in the request body. After updating, the API should return the updated Pet. The API
must return an appropriate error for any constraint violation.

5. Add an event - POST /pets/{id} : The API should create an event for the pet identified by
{id} and return it. The API must return an appropriate error if a Pet identified by {id} does
not exist.

6. Delete a Pet - DELETE /pet : The API should delete the Pet identified by {id} .
