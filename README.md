Steps to run project:

Run MockbusterApplication (Tomcat server, post defined in the resources folder - application.properties)

Add collections to your mongodb database in the admin db and name the collections as they are currently named.
Port is 27017 for mongodb cluster.

I used postman for this exercise. Examples of GET requests that will retrieve the data:

1) http://localhost:8080/mockbuster/customers
- Retrieves the data of every customer as an array list. Should be easy for UI to get any data needed from there.

2) http://localhost:8080/mockbuster/customer?id=1
- Retrieves a single Document with the customer details. All film details can be found under the rentals tag

3) http://localhost:8080/mockbuster/films
- Retrieves all films but only with following information:
a. Title
b. Category
c. Description
d. Rating
e. Rental Duration
 And ID
 
4) http://localhost:8080/mockbuster/film?id=1
- Retrieves a single film with a list of the customers id's who have rented them.

All results can be viewed in postman. Each request is a GET request. 

Assumptions made:
Using the ID for retrieving customers and films. Assumed that we will use admin db and the same collection names for this exercise but can easily be changes.
Returning an array list of documents for the larger lists and individual documents for the specific requests.
Should be easy for UI to use this information.