# revolut-test

## Build and run
1. Run `mvn clean install` to build the application
1. Start application with `java -jar target/revoluttest-1.0-SNAPSHOT.jar server config.yml`
1. To check the application is running enter url `http://localhost:8080`

## Tests
1. To run the tests `mvn test`

## Accessing the API
1. Account info
```
GET /account/1234/info HTTP/1.1
Host: localhost:8080
```
2. Transfer

```
POST /account/transfer HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{"fromAccount":"1234","toAccount":"2345","amount":1000}
```

## Implementation notes
The application utilises Dropwizard, which starts an embedded Jetty service and Jersey REST API.

Test accounts are created on startup (since account creation is not in scope for the task).
 
Account information is stored in a map. To make concurrent transfer requests, the transfer method
is synchronised. This should be acceptable given that the data store is in-memory. However, we 
could improve this by locking on the individual accounts required in each transfer (and obtaining 
these locks in a consistent order, e.g. based on the sorted account numbers). 
