# Restful Github API Wrapper
RESTful wrapper for Github API 


### Run application
```sh
mvn clean install
```

### Example of usage
- GET http://localhost:4567/repositories/andprogrammer/Blockchain


### Endpoints

| METHOD | ENDPOINT | DESCRIPTION |
| -----------| ------ | ------ |
| GET | /repositories/{owner}/{repository-name} | get details of given Github repository |


### Technologies
- unirest (Lightweight HTTP Request Client)
- Spark	(An expressive web framework)
- GSON 	(A Java serialize/deserialize library)
- junit	(JUnit Test Framework)
- json	(Json parser)
- log4j	(Logger)
- jackson (Serialization and deserialization xml)
