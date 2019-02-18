# WebService

Implemented webService Rest in jetty embebed server.

Postman collection : https://www.getpostman.com/collections/4b0be3e71bbf10215622


## URLS

GET - /time - Return timestamp on machine

## how to run application

```
mvn clean
mvn package
cd target
java -jar jetty-webservice-1.0-SNAPSHOT.jar 
```

### Exemple call tu curl command:
```
curl -X GET http://localhost:7000/time -H 'Authorization: opticks_auth_example1' -H 'Content-Type: application/json'
```
