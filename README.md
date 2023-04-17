# seat reservation system

Java Spring REST service that simulates a seat reservation system.
Use an API platform like Postman to send requests.
build instructions

1. Use a terminal to clone the repo: ```git clone https://github.com/natmerem/seatreservation.git```
2. Go to the project folder: ```cd seatreservation```
3. Build the project: ```./gradlew build```
    * [note](https://stackoverflow.com/questions/17668265/gradlew-permission-denied): if you get "permission denied" try using the following command to set the execution flag on the gradlew file:
    * ```chmod +x gradlew```
4. Run the built project: ```./gradlew bootRun```
5. The service should now be up and running! Use an API platform like Postman to make HTTP requests.

<hr>

HTTP REQUESTS

All requests start with: http://localhost:28852
<br>
<br>
```/seats``` \
GET request that returns all seats

```/purchase```  
POST request for purchasing a seat ticket, returns a unique token \
Request body:
```
{
   "row": 9,
   "column": 8
}
```

```/return```  
POST request for returning a ticket, need to specify the token of the seat being returned.  
Request body:
```
{
   "token": "ca2cf70f-750a-4fbe-b838-2f8cb9557780"
}
```

```/stats?password=super_secret```  
POST request for getting seating area statistics, like income earned, seats reserved
