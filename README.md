## Summary

TUI DX Backend technical Test

The base project uses lombok, so you have to install it. You can use the following guide https://www.baeldung.com/lombok-ide

Use Java 11 and Maven 3.6.3 to build the project with the following command:
- mvn clean install

Use docker to run a container with the application image
- docker build -t book-api .
- docker run -d -p 9002:9002 book-api

Test with postman, there is a collection in src / main / resources / postman

The base URL is http://localhost:9002/book, after which there are the following APIs:
- /checkAvailability: Checks for any flights
- /createReservation: create a new reservation without flights
- /addFlight: add a flight to your booking
- /deleteFlight: remove a flight from your booking
- /details/{email}: Returns the details of the reservation associated with the email
- /deleteReservation/{email}: delete the reservation associated with the email
- /confirmReservation/{email}: confirms the reservation if the data is still valid

Technical choices:
- Chose to use the java configuration to have control over certain elements
- Chose to handle exceptions through controller advice for better code cleansing and better reading
- Preferred to use a validation factory to collect the main validations
- Not being able to add external dependencies, the authorization management has been delegated to a method that is 
  called for each exposed Api